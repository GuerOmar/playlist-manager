CREATE TABLE IF NOT EXISTS songs (
                                     id          UUID         NOT NULL DEFAULT RANDOM_UUID(),
    title       VARCHAR(255) NOT NULL,
    artist      VARCHAR(255) NOT NULL,
    genre       VARCHAR(100) NOT NULL,
    duration_seconds INT     NOT NULL,
    CONSTRAINT pk_songs PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS playlists (
                                         id          UUID         NOT NULL DEFAULT RANDOM_UUID(),
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    CONSTRAINT pk_playlists PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS playlist_entries (
                                                id          BIGINT       NOT NULL AUTO_INCREMENT,
                                                position    INT          NOT NULL,
                                                playlist_id UUID         NOT NULL,
                                                song_id     UUID         NOT NULL,
                                                CONSTRAINT pk_playlist_entries PRIMARY KEY (id),
    CONSTRAINT fk_entry_playlist FOREIGN KEY (playlist_id) REFERENCES playlists(id) ON DELETE CASCADE,
    CONSTRAINT fk_entry_song     FOREIGN KEY (song_id)     REFERENCES songs(id)     ON DELETE CASCADE
    );