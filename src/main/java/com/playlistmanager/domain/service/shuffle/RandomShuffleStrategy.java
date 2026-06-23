package com.playlistmanager.domain.service.shuffle;

import com.playlistmanager.domain.model.PlaylistEntry;
import com.playlistmanager.domain.model.ShuffleStrategyType;
import com.playlistmanager.util.PlaylistEntryUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class RandomShuffleStrategy implements ShuffleStrategy {

    @Override
    public ShuffleStrategyType getType() {
        return ShuffleStrategyType.RANDOM;
    }

    @Override
    public List<PlaylistEntry> shuffle(List<PlaylistEntry> entries) {
        List<PlaylistEntry> shuffled = new ArrayList<>(entries);
        Collections.shuffle(shuffled);
        return PlaylistEntryUtils.reindex(shuffled);
    }
}