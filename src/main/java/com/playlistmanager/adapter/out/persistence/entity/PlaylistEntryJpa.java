package com.playlistmanager.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playlist_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistEntryJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", nullable = false)
    private PlaylistJpa playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    private SongJpa song;
}