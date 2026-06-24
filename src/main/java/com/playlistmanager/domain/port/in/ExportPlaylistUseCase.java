package com.playlistmanager.domain.port.in;

import java.util.UUID;

public interface ExportPlaylistUseCase {
    String export(UUID playlistId, String format);
}
