package com.playlistmanager.adapter.in.rest.mapper;

import com.playlistmanager.adapter.in.rest.dto.PlayBackStatusResponse;
import com.playlistmanager.domain.model.PlayBackStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayBackStatusRestMapper {

    private final SongRestMapper songRestMapper;

    public PlayBackStatusResponse toResponse(PlayBackStatus status) {
        return new PlayBackStatusResponse(
                status.getState().name(),
                status.getCurrentPlaylistId(),
                status.getCurrentIndex(),
                status.getTotalSongs(),
                status.getCurrentSong() != null ? songRestMapper.toResponse(status.getCurrentSong()) : null
        );
    }
}