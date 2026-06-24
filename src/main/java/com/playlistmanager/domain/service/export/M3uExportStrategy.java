package com.playlistmanager.domain.service.export;

import com.playlistmanager.domain.model.ExportFormatType;
import com.playlistmanager.domain.model.Playlist;
import com.playlistmanager.domain.model.PlaylistEntry;
import org.springframework.stereotype.Component;

@Component
public class M3uExportStrategy implements ExportStrategy {

    @Override
    public String export(Playlist playlist) {
        StringBuilder sb = new StringBuilder();
        sb.append("#EXTM3U\n");
        sb.append("#PLAYLIST:").append(playlist.getName()).append("\n\n");

        for (PlaylistEntry entry : playlist.getEntries()) {
            sb.append("#EXTINF:")
                    .append(entry.getSong().getDurationSeconds())
                    .append(",")
                    .append(entry.getSong().getArtist())
                    .append(" - ")
                    .append(entry.getSong().getTitle())
                    .append("\n");
            sb.append(entry.getSong().getTitle()).append("\n\n");
        }

        return sb.toString();
    }

    @Override
    public ExportFormatType getFormat() {
        return ExportFormatType.M3U;
    }
}