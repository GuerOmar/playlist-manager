package com.playlistmanager.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaylistEntry {
    private int position;
    private Song song;
}