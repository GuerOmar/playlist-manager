package com.playlistmanager.domain.port.out;

import com.playlistmanager.domain.model.Playlist;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaylistPersistencePort {
    Playlist save(Playlist playlist);
    Optional<Playlist> findById(UUID id);
    List<Playlist> findAll();
    void deleteById(UUID id);
    boolean existsById(UUID id);
}