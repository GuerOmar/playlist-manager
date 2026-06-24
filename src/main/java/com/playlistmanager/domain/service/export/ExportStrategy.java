package com.playlistmanager.domain.service.export;

import com.playlistmanager.domain.model.ExportFormatType;
import com.playlistmanager.domain.model.Playlist;

public interface ExportStrategy {
    String export(Playlist playlist);
    ExportFormatType getFormat();
}