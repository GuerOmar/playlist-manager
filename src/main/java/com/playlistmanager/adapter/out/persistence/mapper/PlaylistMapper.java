package com.playlistmanager.adapter.out.persistence.mapper;

import com.playlistmanager.adapter.out.persistence.entity.PlaylistEntryJpa;
import com.playlistmanager.adapter.out.persistence.entity.PlaylistJpa;
import com.playlistmanager.domain.model.Playlist;
import com.playlistmanager.domain.model.PlaylistEntry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaylistMapper {

    private final SongMapper songMapper;

    public PlaylistMapper(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    public Playlist toDomain(PlaylistJpa jpa) {
        Playlist playlist = Playlist.builder()
                .id(jpa.getId())
                .name(jpa.getName())
                .description(jpa.getDescription())
                .build();

        List<PlaylistEntry> entries = jpa.getEntries().stream()
                .map(e -> PlaylistEntry.builder()
                        .position(e.getPosition())
                        .song(songMapper.toDomain(e.getSong()))
                        .build()
                )
                .toList();

        playlist.setEntries(entries);
        return playlist;
    }

    public PlaylistJpa toJpa(Playlist domain) {
        PlaylistJpa playlistJpa = new PlaylistJpa(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                null
        );

        List<PlaylistEntryJpa> entries = domain.getEntries().stream()
                .map(e -> new PlaylistEntryJpa(
                        null,
                        e.getPosition(),
                        playlistJpa,
                        songMapper.toJpa(e.getSong())
                ))
                .toList();

        playlistJpa.setEntries(entries);
        return playlistJpa;
    }
}