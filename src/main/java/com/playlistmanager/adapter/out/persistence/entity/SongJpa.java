package com.playlistmanager.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "songs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private int durationSeconds;
}