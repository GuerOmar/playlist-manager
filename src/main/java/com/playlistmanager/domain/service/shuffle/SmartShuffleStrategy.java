package com.playlistmanager.domain.service.shuffle;

import com.playlistmanager.domain.model.PlaylistEntry;
import com.playlistmanager.domain.model.ShuffleStrategyType;
import com.playlistmanager.util.PlaylistEntryUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class SmartShuffleStrategy implements ShuffleStrategy {

    @Override
    public ShuffleStrategyType getType() {
        return ShuffleStrategyType.SMART;
    }

    @Override
    public List<PlaylistEntry> shuffle(List<PlaylistEntry> entries) {
        List<PlaylistEntry> remaining = new LinkedList<>(entries);
        Collections.shuffle(remaining);

        List<PlaylistEntry> shuffled = new ArrayList<>();

        while (!remaining.isEmpty()) {
            String lastArtist = shuffled.isEmpty() ? null : shuffled.getLast().getSong().getArtist();

            PlaylistEntry next = null;
            for (PlaylistEntry candidate : remaining) {
                if (!candidate.getSong().getArtist().equals(lastArtist)) {
                    next = candidate;
                    break;
                }
            }

            if (next == null) {
                shuffled.addAll(remaining);
                break;
            }

            remaining.remove(next);
            shuffled.add(next);
        }

        return PlaylistEntryUtils.reindex(shuffled);
    }
}