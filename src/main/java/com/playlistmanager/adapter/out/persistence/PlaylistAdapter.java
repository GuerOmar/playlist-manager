package com.playlistmanager.adapter.out.persistence;


import com.playlistmanager.adapter.out.persistence.mapper.PlaylistMapper;
import com.playlistmanager.adapter.out.persistence.repository.PlaylistRepository;
import com.playlistmanager.domain.model.Playlist;
import com.playlistmanager.domain.port.out.PlaylistPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlaylistAdapter implements PlaylistPersistencePort {

    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;

    @Override
    public Playlist save(Playlist playlist) {
        return playlistMapper.toDomain(playlistRepository.save(playlistMapper.toJpa(playlist)));
    }

    @Override
    public Optional<Playlist> findById(UUID id) {
        return playlistRepository.findById(id).map(playlistMapper::toDomain);
    }

    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll().stream()
                .map(playlistMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return playlistRepository.existsById(id);
    }
}