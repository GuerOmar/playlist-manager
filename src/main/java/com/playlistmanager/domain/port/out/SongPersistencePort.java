package com.playlistmanager.domain.port.out;

import com.playlistmanager.domain.model.Song;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SongPersistencePort {
    Song save(Song song);
    Optional<Song> findById(UUID id);
    List<Song> findAll();
    void deleteById(UUID id);
    boolean existsById(UUID id);
}