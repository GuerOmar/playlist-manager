package com.playlistmanager.domain.service;

import com.playlistmanager.domain.model.Playlist;
import com.playlistmanager.domain.model.ShuffleStrategyType;
import com.playlistmanager.domain.port.in.ShufflePlaylistUseCase;
import com.playlistmanager.domain.port.out.PlaylistPersistencePort;
import com.playlistmanager.domain.service.shuffle.ShuffleStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShufflePlaylistService implements ShufflePlaylistUseCase {

    private final Map<ShuffleStrategyType, ShuffleStrategy> strategies;
    private final PlaylistPersistencePort playlistPersistencePort;

    public ShufflePlaylistService(PlaylistPersistencePort playlistPersistencePort, List<ShuffleStrategy> strategyList) {
        this.playlistPersistencePort = playlistPersistencePort;
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(ShuffleStrategy::getType, s -> s));
    }

    public Playlist shuffle(UUID playlistId, String strategyType) {
        ShuffleStrategyType type = ShuffleStrategyType.valueOf(strategyType.toUpperCase());
        ShuffleStrategy strategy = strategies.get(type);

        Playlist playlist = playlistPersistencePort.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found: " + playlistId));

        playlist.setEntries(strategy.shuffle(playlist.getEntries()));
        return playlistPersistencePort.save(playlist);
    }
}