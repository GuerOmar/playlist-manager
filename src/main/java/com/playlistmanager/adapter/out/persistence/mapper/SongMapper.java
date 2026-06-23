package com.playlistmanager.adapter.out.persistence.mapper;

import com.playlistmanager.adapter.out.persistence.entity.SongJpa;
import com.playlistmanager.domain.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {

    public Song toDomain(SongJpa jpa) {
        return Song.builder()
                .id(jpa.getId())
                .title(jpa.getTitle())
                .artist(jpa.getArtist())
                .genre(jpa.getGenre())
                .durationSeconds(jpa.getDurationSeconds())
                .build();
    }

    public SongJpa toJpa(Song domain) {
        return new SongJpa(
                domain.getId(),
                domain.getTitle(),
                domain.getArtist(),
                domain.getGenre(),
                domain.getDurationSeconds()
        );
    }
}