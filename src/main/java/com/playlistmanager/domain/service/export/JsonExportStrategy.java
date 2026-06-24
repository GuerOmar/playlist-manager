package com.playlistmanager.domain.service.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playlistmanager.domain.model.ExportFormatType;
import com.playlistmanager.domain.model.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonExportStrategy implements ExportStrategy {

    private final ObjectMapper objectMapper;

    @Override
    public String export(Playlist playlist) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(playlist);
        } catch (Exception e) {
            throw new RuntimeException("Failed to export playlist as JSON", e);
        }
    }

    @Override
    public ExportFormatType getFormat() {
        return ExportFormatType.JSON;
    }
}