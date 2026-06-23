package com.playlistmanager.domain.service;

import com.playlistmanager.domain.model.Playlist;
import com.playlistmanager.domain.model.PlaylistEntry;
import com.playlistmanager.domain.model.Song;
import com.playlistmanager.domain.port.in.PlaylistUseCase;
import com.playlistmanager.domain.port.out.PlaylistPersistencePort;
import com.playlistmanager.domain.port.out.SongPersistencePort;
import com.playlistmanager.util.PlaylistEntryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaylistService implements PlaylistUseCase {

    private final PlaylistPersistencePort playlistPersistencePort;
    private final SongPersistencePort songPersistencePort;

    @Override
    public Playlist create(String name, String description) {
        Playlist playlist = Playlist.builder()
                .name(name)
                .description(description)
                .build();
        return playlistPersistencePort.save(playlist);
    }

    @Override
    @Transactional(readOnly = true)
    public Playlist getById(UUID id) {
        return playlistPersistencePort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> getAll() {
        return playlistPersistencePort.findAll();
    }

    @Override
    public Playlist update(UUID id, String name, String description) {
        Playlist playlist = getById(id);
        playlist.setName(name);
        playlist.setDescription(description);
        return playlistPersistencePort.save(playlist);
    }

    @Override
    public void delete(UUID id) {
        if (!playlistPersistencePort.existsById(id)) {
            throw new IllegalArgumentException("Playlist not found: " + id);
        }
        playlistPersistencePort.deleteById(id);
    }

    @Override
    public Playlist addSong(UUID playlistId, UUID songId) {
        Playlist playlist = getById(playlistId);
        Song song = songPersistencePort.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("Song not found: " + songId));

        boolean alreadyAdded = playlist.getEntries().stream()
                .anyMatch(e -> e.getSong().getId().equals(songId));
        if (alreadyAdded) {
            throw new IllegalStateException("Song already in playlist");
        }

        int nextPosition = playlist.getEntries().size();
        playlist.getEntries().add(
                PlaylistEntry.builder()
                        .position(nextPosition)
                        .song(song)
                        .build()
        );
        return playlistPersistencePort.save(playlist);
    }

    @Override
    public Playlist removeSong(UUID playlistId, UUID songId) {
        Playlist playlist = getById(playlistId);

        PlaylistEntry entry = playlist.getEntries().stream()
                .filter(e -> e.getSong().getId().equals(songId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Song not found in playlist"));

        playlist.getEntries().remove(entry);
        PlaylistEntryUtils.reindex(playlist.getEntries());
        return playlistPersistencePort.save(playlist);
    }

    @Override
    public Playlist reorderSong(UUID playlistId, UUID songId, int newPosition) {
        if (newPosition < 0) {
            throw new IllegalArgumentException("Position invalid: " + newPosition);
        }

        Playlist playlist = getById(playlistId);



        PlaylistEntry entry = playlist.getEntries().stream()
                .filter(e -> e.getSong().getId().equals(songId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Song not found in playlist"));

        int maxPosition = Math.min(newPosition, playlist.getEntries().size());
        playlist.getEntries().remove(entry);
        playlist.getEntries().add(maxPosition, entry);
        PlaylistEntryUtils.reindex(playlist.getEntries());
        return playlistPersistencePort.save(playlist);
    }
}