package com.playlistmanager.adapter.in.rest.dto;


public record SongRequest(
        String title,
        String artist,
        String genre,
        int durationSeconds
) {}