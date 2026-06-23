package com.playlistmanager.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Playlist {
    private UUID id;
    private String name;
    private String description;
    private List<PlaylistEntry> entries = new ArrayList<>();
}