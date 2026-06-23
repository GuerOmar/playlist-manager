package com.playlistmanager.adapter.in.rest.mapper;

import com.playlistmanager.adapter.in.rest.dto.SongResponse;
import com.playlistmanager.domain.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SongRestMapper {

    public SongResponse toResponse(Song song) {
        return new SongResponse(
                song.getId(),
                song.getTitle(),
                song.getArtist(),
                song.getGenre(),
                song.getDurationSeconds()
        );
    }
}