package com.playlistmanager.adapter.in.rest.mapper;

import com.playlistmanager.adapter.in.rest.dto.PlaylistEntryResponse;
import com.playlistmanager.adapter.in.rest.dto.PlaylistResponse;
import com.playlistmanager.domain.model.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaylistRestMapper {

    private final SongRestMapper songRestMapper;

    public PlaylistResponse toResponse(Playlist playlist) {
        return new PlaylistResponse(
                playlist.getId(),
                playlist.getName(),
                playlist.getDescription(),
                playlist.getEntries().stream()
                        .map(e -> new PlaylistEntryResponse(e.getPosition(), songRestMapper.toResponse(e.getSong())))
                        .toList()
        );
    }
}