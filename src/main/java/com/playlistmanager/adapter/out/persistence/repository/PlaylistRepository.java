package com.playlistmanager.adapter.out.persistence.repository;

import com.playlistmanager.adapter.out.persistence.entity.PlaylistJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PlaylistRepository extends JpaRepository<PlaylistJpa, UUID> {}