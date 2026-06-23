package com.playlistmanager.adapter.in.rest.dto;

import java.util.UUID;

public record SongResponse(
        UUID id,
        String title,
        String artist,
        String genre,
        int durationSeconds
) {}