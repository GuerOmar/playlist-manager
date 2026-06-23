package com.playlistmanager.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Song {
    private UUID id;
    private String title;
    private String artist;
    private String genre;
    private int durationSeconds;
}