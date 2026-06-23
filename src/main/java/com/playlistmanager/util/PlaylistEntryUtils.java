package com.playlistmanager.util;

import com.playlistmanager.domain.model.PlaylistEntry;

import java.util.List;

public class PlaylistEntryUtils {

    private PlaylistEntryUtils() {}

    public static List<PlaylistEntry> reindex(List<PlaylistEntry> entries) {
        for (int i = 0; i < entries.size(); i++) {
            entries.get(i).setPosition(i);
        }
        return entries;
    }
}
