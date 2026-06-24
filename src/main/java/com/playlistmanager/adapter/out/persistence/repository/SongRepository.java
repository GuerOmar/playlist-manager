package com.playlistmanager.adapter.out.persistence.repository;

import com.playlistmanager.adapter.out.persistence.entity.SongJpa;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface SongRepository extends JpaRepository<SongJpa, UUID> {

    @Query("SELECT s FROM SongJpa s WHERE s.artist = :artist AND s.id NOT IN :excludedIds")
    List<SongJpa> findByArtistNotInIds(@Param("artist") String artist, @Param("excludedIds") Set<UUID> excludedIds, Limit limit);
}