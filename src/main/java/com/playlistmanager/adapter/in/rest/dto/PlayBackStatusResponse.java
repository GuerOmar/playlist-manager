package com.playlistmanager.adapter.in.rest.dto;

import java.util.UUID;

public record PlayBackStatusResponse(
        String state,
        UUID currentPlaylistId,
        int currentIndex,
        int totalSongs,
        SongResponse currentSong
) {}