package com.playlistmanager.adapter.in.rest.dto;

import java.util.List;
import java.util.UUID;

public record PlaylistResponse(
        UUID id,
        String name,
        String description,
        List<PlaylistEntryResponse> entries
) {}