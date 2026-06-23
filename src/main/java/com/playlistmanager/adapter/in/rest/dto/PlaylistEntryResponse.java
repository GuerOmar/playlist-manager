package com.playlistmanager.adapter.in.rest.dto;

public record PlaylistEntryResponse(
        int position,
        SongResponse song
) {}