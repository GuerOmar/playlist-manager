package com.playlistmanager.domain.port.in;

import com.playlistmanager.domain.model.Playlist;
import java.util.List;
import java.util.UUID;

public interface PlaylistUseCase {
    Playlist create(String name, String description);
    Playlist getById(UUID id);
    List<Playlist> getAll();
    Playlist update(UUID id, String name, String description);
    void delete(UUID id);
    Playlist addSong(UUID playlistId, UUID songId);
    Playlist removeSong(UUID playlistId, UUID songId);
    Playlist reorderSong(UUID playlistId, UUID songId, int newPosition);
}