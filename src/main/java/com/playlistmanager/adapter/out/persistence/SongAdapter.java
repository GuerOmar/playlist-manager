package com.playlistmanager.adapter.out.persistence;

import com.playlistmanager.adapter.out.persistence.mapper.SongMapper;
import com.playlistmanager.adapter.out.persistence.repository.SongRepository;
import com.playlistmanager.domain.model.Song;
import com.playlistmanager.domain.port.out.SongPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SongAdapter implements SongPersistencePort {

    private final SongRepository songRepository;
    private final SongMapper songMapper;

    @Override
    public Song save(Song song) {
        return songMapper.toDomain(songRepository.save(songMapper.toJpa(song)));
    }

    @Override
    public Optional<Song> findById(UUID id) {
        return songRepository.findById(id).map(songMapper::toDomain);
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll().stream()
                .map(songMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        songRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return songRepository.existsById(id);
    }
}