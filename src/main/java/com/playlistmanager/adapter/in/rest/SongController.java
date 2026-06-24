package com.playlistmanager.adapter.in.rest;

import com.playlistmanager.adapter.in.rest.dto.SongRequest;
import com.playlistmanager.adapter.in.rest.dto.SongResponse;
import com.playlistmanager.adapter.in.rest.mapper.SongRestMapper;
import com.playlistmanager.domain.port.in.RecommendationUseCase;
import com.playlistmanager.domain.port.in.SongUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongUseCase songUseCase;
    private final SongRestMapper songRestMapper;
    private final RecommendationUseCase recommendationUseCase;

    @GetMapping
    public List<SongResponse> getAll() {
        return songUseCase.getAll().stream()
                .map(songRestMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public SongResponse getById(@PathVariable UUID id) {
        return songRestMapper.toResponse(songUseCase.getById(id));
    }

    @PostMapping
    public SongResponse create(@RequestBody SongRequest request) {
        return songRestMapper.toResponse(
                songUseCase.create(
                        request.title(),
                        request.artist(),
                        request.genre(),
                        request.durationSeconds()
                )
        );
    }

    @PutMapping("/{id}")
    public SongResponse update(@PathVariable UUID id, @RequestBody SongRequest request) {
        return songRestMapper.toResponse(
                songUseCase.update(
                        id,
                        request.title(),
                        request.artist(),
                        request.genre(),
                        request.durationSeconds()
                )
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        songUseCase.delete(id);
    }

    @GetMapping("/recommendations/{playlistId}")
    public List<SongResponse> recommend(
            @PathVariable UUID playlistId,
            @RequestParam(defaultValue = "10") int limit) {
        return recommendationUseCase.recommend(playlistId, limit)
                .stream()
                .map(songRestMapper::toResponse)
                .toList();
    }
}