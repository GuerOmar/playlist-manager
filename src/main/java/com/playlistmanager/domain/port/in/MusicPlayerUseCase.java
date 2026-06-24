package com.playlistmanager.domain.port.in;

import com.playlistmanager.domain.model.PlayBackStatus;

import java.util.UUID;

public interface MusicPlayerUseCase {
    PlayBackStatus play(UUID playlistId);
    PlayBackStatus pause();
    PlayBackStatus stop();
    PlayBackStatus next();
    PlayBackStatus previous();
    PlayBackStatus status();
}