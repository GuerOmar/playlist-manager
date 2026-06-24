package com.playlistmanager.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PlayBackStatus {
    private MusicPlayerState state;
    private UUID currentPlaylistId;
    private int currentIndex;
    private int totalSongs;
    private Song currentSong;
}