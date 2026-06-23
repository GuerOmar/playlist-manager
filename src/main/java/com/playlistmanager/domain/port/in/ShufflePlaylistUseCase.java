package com.playlistmanager.domain.port.in;

import com.playlistmanager.domain.model.Playlist;

import java.util.UUID;

public interface ShufflePlaylistUseCase {
    Playlist shuffle(UUID playlistId, String type);
}
