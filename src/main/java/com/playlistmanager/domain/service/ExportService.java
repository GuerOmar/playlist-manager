package com.playlistmanager.domain.service;

import com.playlistmanager.domain.model.ExportFormatType;
import com.playlistmanager.domain.model.Playlist;
import com.playlistmanager.domain.port.in.ExportPlaylistUseCase;
import com.playlistmanager.domain.port.out.PlaylistPersistencePort;
import com.playlistmanager.domain.service.export.ExportStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExportService implements ExportPlaylistUseCase {

    private final Map<ExportFormatType, ExportStrategy> strategies;
    private final PlaylistPersistencePort playlistPersistencePort;

    public ExportService(List<ExportStrategy> strategyList, PlaylistPersistencePort playlistPersistencePort) {
        this.playlistPersistencePort = playlistPersistencePort;
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(ExportStrategy::getFormat, s -> s));
    }

    @Override
    public String export(UUID playlistId, String format) {
        ExportFormatType formatType = ExportFormatType.valueOf(format);
        ExportStrategy strategy = strategies.get(formatType);

        Playlist playlist = playlistPersistencePort.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found: " + playlistId));

        return strategy.export(playlist);
    }
}