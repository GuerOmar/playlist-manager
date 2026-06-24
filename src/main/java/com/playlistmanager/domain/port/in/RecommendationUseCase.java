package com.playlistmanager.domain.port.in;

import com.playlistmanager.domain.model.Song;
import java.util.List;
import java.util.UUID;

public interface RecommendationUseCase {
    List<Song> recommend(UUID playlistId, int limit);
}