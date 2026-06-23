package com.playlistmanager.adapter.out.persistence.repository;

import com.playlistmanager.adapter.out.persistence.entity.SongJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SongRepository extends JpaRepository<SongJpa, UUID> {}