package com.playlistmanager.adapter.in.rest;

import com.playlistmanager.adapter.in.rest.dto.PlaylistRequest;
import com.playlistmanager.adapter.in.rest.dto.PlaylistResponse;
import com.playlistmanager.adapter.in.rest.mapper.PlaylistRestMapper;
import com.playlistmanager.domain.port.in.PlaylistUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistUseCase playlistUseCase;
    private final PlaylistRestMapper playlistRestMapper;

    @GetMapping
    public List<PlaylistResponse> getAll() {
        return playlistUseCase.getAll().stream()
                .map(playlistRestMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public PlaylistResponse getById(@PathVariable UUID id) {
        return playlistRestMapper.toResponse(playlistUseCase.getById(id));
    }

    @PostMapping
    public PlaylistResponse create(@RequestBody PlaylistRequest request) {
        return playlistRestMapper.toResponse(
                playlistUseCase.create(request.name(), request.description())
        );
    }

    @PutMapping("/{id}")
    public PlaylistResponse update(@PathVariable UUID id, @RequestBody PlaylistRequest request) {
        return playlistRestMapper.toResponse(
                playlistUseCase.update(id, request.name(), request.description())
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        playlistUseCase.delete(id);
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    public PlaylistResponse addSong(@PathVariable UUID playlistId, @PathVariable UUID songId) {
        return playlistRestMapper.toResponse(playlistUseCase.addSong(playlistId, songId));
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    public PlaylistResponse removeSong(@PathVariable UUID playlistId, @PathVariable UUID songId) {
        return playlistRestMapper.toResponse(playlistUseCase.removeSong(playlistId, songId));
    }

    @PatchMapping("/{playlistId}/songs/reorder/{songId}")
    public PlaylistResponse reorderSong(
            @PathVariable UUID playlistId,
            @PathVariable UUID songId,
            @RequestParam int newPosition) {
        return playlistRestMapper.toResponse(playlistUseCase.reorderSong(playlistId, songId, newPosition));
    }
}