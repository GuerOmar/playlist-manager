package com.playlistmanager.domain.service;

import com.playlistmanager.domain.model.Song;
import com.playlistmanager.domain.port.in.SongUseCase;
import com.playlistmanager.domain.port.out.SongPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SongService implements SongUseCase {

    private final SongPersistencePort songPersistencePort;

    @Override
    public Song create(String title, String artist, String genre, int durationSeconds) {
        Song song = Song.builder()
                .title(title)
                .artist(artist)
                .genre(genre)
                .durationSeconds(durationSeconds)
                .build();
        return songPersistencePort.save(song);
    }

    @Override
    @Transactional(readOnly = true)
    public Song getById(UUID id) {
        return songPersistencePort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Song not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Song> getAll() {
        return songPersistencePort.findAll();
    }

    @Override
    public Song update(UUID id, String title, String artist, String genre, int durationSeconds) {
        Song song = getById(id);
        song.setTitle(title);
        song.setArtist(artist);
        song.setGenre(genre);
        song.setDurationSeconds(durationSeconds);
        return songPersistencePort.save(song);
    }

    @Override
    public void delete(UUID id) {
        if (!songPersistencePort.existsById(id)) {
            throw new IllegalArgumentException("Song not found: " + id);
        }
        songPersistencePort.deleteById(id);
    }
}