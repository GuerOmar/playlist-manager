# Playlist Manager

A RESTful API for managing music playlists built with Java 21 and Spring Boot 3.5.15, following a hexagonal architecture.

## Features

### Song Management
Full CRUD operations for songs, each with a title, artist, genre and duration.

### Playlist Management
Create and manage playlists with dynamic song addition, removal and reordering. Positions are automatically recalculated after any change.

### Shuffle Strategies
Shuffle a playlist at runtime using one of three algorithms:
- **RANDOM** — Fully random.
- **SMART** — Avoids consecutive songs from the same artist
- **GENRE_BALANCED** — Interleaves songs evenly across genres

### Recommendations
Suggest songs based on the artist distribution of a playlist. Songs already present are excluded.

### Export
Export a playlist in two formats:
- **JSON** — Standard JSON representation of the playlist.
- **M3U** — Standard media playlist format.

### Music Player
A global single instance player managing playback state across the application. Supports the following operations:
- **Play** — Load a new playlist or resume the current one
- **Pause** — Pause the current song
- **Stop** — Stop playback and clear the current playlist
- **Next / Previous** — Navigate between songs
- **Status** — Get the current song

## Getting Started
For all available endpoints, request/response models and live testing, check the Swagger UI once the application is running `http://localhost:8080/swagger-ui/index.html`.

## Database

The application uses **H2 in file mode**, meaning data persists across restarts. The schema and seed data are initialized from:

- `src/main/resources/db/schema.sql` — table definitions
- `src/main/resources/db/data.sql` — sample songs and playlists

Both scripts are safe to run on every startup.