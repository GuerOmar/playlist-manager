package com.playlistmanager.domain.service;

import com.playlistmanager.domain.model.MusicPlayerState;
import com.playlistmanager.domain.model.PlayBackStatus;
import com.playlistmanager.domain.model.Playlist;
import com.playlistmanager.domain.port.in.MusicPlayerUseCase;
import com.playlistmanager.domain.port.out.PlaylistPersistencePort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class MusicPlayerService implements MusicPlayerUseCase {

    private final PlaylistPersistencePort playlistPersistencePort;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private Playlist currentPlaylist;
    private volatile MusicPlayerState state = MusicPlayerState.STOPPED;
    private int currentIndex = 0;

    public MusicPlayerService(PlaylistPersistencePort playlistPersistencePort) {
        this.playlistPersistencePort = playlistPersistencePort;
    }

    @Override
    public PlayBackStatus play(UUID playlistId) {
        lock.writeLock().lock();
        try {
            if (playlistId != null) {
                boolean isDifferent = currentPlaylist == null || !currentPlaylist.getId().equals(playlistId);
                if (isDifferent) {
                    Playlist newPlaylist = playlistPersistencePort.findById(playlistId)
                            .orElseThrow(() -> new IllegalArgumentException("Playlist not found: " + playlistId));
                    if (newPlaylist.getEntries().isEmpty()) {
                        throw new IllegalStateException("Playlist is empty");
                    }
                    currentPlaylist = newPlaylist;
                    currentIndex = 0;
                }
            } else {
                if (currentPlaylist == null) {
                    throw new IllegalStateException("No playlist loaded");
                }
            }
            state = MusicPlayerState.PLAYING;
            return buildStatus();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public PlayBackStatus pause() {
        lock.readLock().lock();
        try {
            state = MusicPlayerState.PAUSED;
            return buildStatus();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public PlayBackStatus stop() {
        lock.writeLock().lock();
        try {
            state = MusicPlayerState.STOPPED;
            currentPlaylist = null;
            currentIndex = 0;
            return buildStatus();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public PlayBackStatus next() {
        lock.writeLock().lock();
        try {
            if (currentPlaylist == null) {
                throw new IllegalStateException("No playlist loaded");
            }
            if (currentIndex + 1 >= currentPlaylist.getEntries().size()) {
                throw new IllegalStateException("Already at the last song");
            }
            currentIndex++;
            return buildStatus();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public PlayBackStatus previous() {
        lock.writeLock().lock();
        try {
            if (currentPlaylist == null) {
                throw new IllegalStateException("No playlist loaded");
            }

            if (currentIndex - 1 < 0) {
                throw new IllegalStateException("Already at the first song");
            }
            currentIndex--;
            return buildStatus();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public PlayBackStatus status() {
        lock.readLock().lock();
        try {
            return buildStatus();
        } finally {
            lock.readLock().unlock();
        }
    }

    private PlayBackStatus buildStatus() {
        if (currentPlaylist == null) {
            return PlayBackStatus.builder()
                    .state(state)
                    .build();
        }
        return PlayBackStatus.builder()
                .state(state)
                .currentPlaylistId(currentPlaylist.getId())
                .currentIndex(currentIndex)
                .totalSongs(currentPlaylist.getEntries().size())
                .currentSong(Optional.of(currentPlaylist).map(cp -> cp.getEntries().get(currentIndex).getSong()).orElse(null))
                .build();
    }
}