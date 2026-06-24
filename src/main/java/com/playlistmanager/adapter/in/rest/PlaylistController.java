package com.playlistmanager.adapter.in.rest;

import com.playlistmanager.adapter.in.rest.dto.PlaylistRequest;
import com.playlistmanager.adapter.in.rest.dto.PlaylistResponse;
import com.playlistmanager.adapter.in.rest.mapper.PlaylistRestMapper;
import com.playlistmanager.domain.model.ExportFormatType;
import com.playlistmanager.domain.model.ShuffleStrategyType;
import com.playlistmanager.domain.port.in.PlaylistUseCase;
import com.playlistmanager.domain.port.in.ShufflePlaylistUseCase;
import com.playlistmanager.domain.service.ExportService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistUseCase playlistUseCase;
    private final PlaylistRestMapper playlistRestMapper;
    private final ShufflePlaylistUseCase shufflePlaylistUseCase;
    private final ExportService exportService;

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

    @PostMapping("/shuffle/{id}")
    public PlaylistResponse shuffle(
            @PathVariable UUID id,
            @Parameter(
                    schema = @Schema(implementation = ShuffleStrategyType.class)
            )
            @RequestParam String strategy) {
        return playlistRestMapper.toResponse(shufflePlaylistUseCase.shuffle(id, strategy));
    }

    @GetMapping("/export/{playlistId}")
    public ResponseEntity<String> export(
            @PathVariable UUID playlistId,
            @Parameter(description = "Export format", schema = @Schema(implementation = ExportFormatType.class))
            @RequestParam String format) {

        String result = exportService.export(playlistId, format);

        String contentType = switch (format) {
            case "JSON" -> "application/json";
            case "M3U" -> "audio/x-mpegurl";
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };

        String extension = switch (format) {
            case "JSON" -> "json";
            case "M3U" -> "m3u";
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + playlistId + "." + extension + "\"")
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(result);
    }
}