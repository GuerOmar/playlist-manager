package com.playlistmanager.adapter.in.rest;

import com.playlistmanager.adapter.in.rest.dto.PlayBackStatusResponse;
import com.playlistmanager.adapter.in.rest.mapper.PlayBackStatusRestMapper;
import com.playlistmanager.domain.port.in.MusicPlayerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/musicplayer")
@RequiredArgsConstructor
public class MusicPlayerController {

    private final MusicPlayerUseCase musicPlayerUseCase;
    private final PlayBackStatusRestMapper playBackStatusRestMapper;

    @PostMapping("/play")
    public PlayBackStatusResponse play(@RequestParam(required = false) UUID playlistId) {
        return playBackStatusRestMapper.toResponse(musicPlayerUseCase.play(playlistId));
    }

    @PatchMapping("/pause")
    public PlayBackStatusResponse pause() {
        return playBackStatusRestMapper.toResponse(musicPlayerUseCase.pause());
    }

    @DeleteMapping("/stop")
    public PlayBackStatusResponse stop() {
        return playBackStatusRestMapper.toResponse(musicPlayerUseCase.stop());
    }

    @PatchMapping("/next")
    public PlayBackStatusResponse next() {
        return playBackStatusRestMapper.toResponse(musicPlayerUseCase.next());
    }

    @PatchMapping("/previous")
    public PlayBackStatusResponse previous() {
        return playBackStatusRestMapper.toResponse(musicPlayerUseCase.previous());
    }

    @GetMapping("/status")
    public PlayBackStatusResponse status() {
        return playBackStatusRestMapper.toResponse(musicPlayerUseCase.status());
    }
}