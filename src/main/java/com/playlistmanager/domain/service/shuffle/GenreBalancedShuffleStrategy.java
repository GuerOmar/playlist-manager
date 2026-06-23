package com.playlistmanager.domain.service.shuffle;

import com.playlistmanager.domain.model.PlaylistEntry;
import com.playlistmanager.domain.model.ShuffleStrategyType;
import com.playlistmanager.util.PlaylistEntryUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GenreBalancedShuffleStrategy implements ShuffleStrategy {

    @Override
    public ShuffleStrategyType getType() {
        return ShuffleStrategyType.GENRE_BALANCED;
    }

    @Override
    public List<PlaylistEntry> shuffle(List<PlaylistEntry> entries) {
        Map<String, List<PlaylistEntry>> byGenre = entries.stream()
                .collect(Collectors.groupingBy(e -> e.getSong().getGenre()));

        byGenre.values().forEach(Collections::shuffle);

        List<PlaylistEntry> shuffled = new ArrayList<>();
        while (!byGenre.isEmpty()) {
            List<String> remove = new ArrayList<>();
            for (Map.Entry<String, List<PlaylistEntry>> entry : byGenre.entrySet()) {
                shuffled.add(entry.getValue().getLast());
                entry.getValue().removeLast();
                if (entry.getValue().isEmpty()) {
                    remove.add(entry.getKey());
                }
            }
            for(String key : remove) {
                byGenre.remove(key);
            }
        }

        return PlaylistEntryUtils.reindex(shuffled);
    }
}