package com.playlistmanager.domain.service.shuffle;

import com.playlistmanager.domain.model.PlaylistEntry;
import com.playlistmanager.domain.model.ShuffleStrategyType;

import java.util.List;

public interface ShuffleStrategy {
    ShuffleStrategyType getType();
    List<PlaylistEntry> shuffle(List<PlaylistEntry> entries);
}