package com.playlistmanager.domain.port.in;

import com.playlistmanager.domain.model.Song;
import java.util.List;
import java.util.UUID;

public interface SongUseCase {
    Song create(String title, String artist, String genre, int durationSeconds);
    Song getById(UUID id);
    List<Song> getAll();
    Song update(UUID id, String title, String artist, String genre, int durationSeconds);
    void delete(UUID id);
}