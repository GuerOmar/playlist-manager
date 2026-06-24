package com.playlistmanager.domain.service;

import com.playlistmanager.domain.model.Playlist;
import com.playlistmanager.domain.model.PlaylistEntry;
import com.playlistmanager.domain.model.Song;
import com.playlistmanager.domain.port.in.RecommendationUseCase;
import com.playlistmanager.domain.port.out.PlaylistPersistencePort;
import com.playlistmanager.domain.port.out.SongPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendationService implements RecommendationUseCase {

    private final PlaylistPersistencePort playlistPersistencePort;
    private final SongPersistencePort songPersistencePort;

    @Override
    public List<Song> recommend(UUID playlistId, int limit) {
        Playlist playlist = playlistPersistencePort.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found: " + playlistId));

        List<PlaylistEntry> entries = playlist.getEntries();
        int playlistSize = entries.size();

        if (playlistSize == 0) {
            return List.of();
        }

        Set<UUID> existingIds = entries.stream()
                .map(e -> e.getSong().getId())
                .collect(Collectors.toSet());

        Map<String, Long> artistFrequency = entries.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getSong().getArtist(),
                        Collectors.counting()
                ));

        List<Map.Entry<String, Long>> artistsByOccurrence = artistFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .toList();

        List<Song> recommendations = new ArrayList<>();
        int remaining = Math.max(10, limit);

        for (Map.Entry<String, Long> artistEntry : artistsByOccurrence) {
            if (remaining == 0) break;

            String artist = artistEntry.getKey();
            long count = artistEntry.getValue();

            double percentage = (double) count / playlistSize;

            int k = (int) Math.ceil(percentage * limit);
            k = Math.min(k, remaining);

            List<Song> songs = songPersistencePort.findByArtistNotInIds(artist, existingIds, k);
            recommendations.addAll(songs);
            remaining -= songs.size();
        }

        return recommendations;
    }
}