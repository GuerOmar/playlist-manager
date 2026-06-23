MERGE INTO songs (id, title, artist, genre, duration_seconds) KEY(id) VALUES
    ('a1b2c3d4-0001-0001-0001-000000000001', 'Bohemian Rhapsody',  'Queen',            'Rock',  354),
    ('a1b2c3d4-0002-0002-0002-000000000002', 'Hotel California',   'Eagles',           'Rock',  391),
    ('a1b2c3d4-0003-0003-0003-000000000003', 'Blinding Lights',    'The Weeknd',       'Pop',   200),
    ('a1b2c3d4-0004-0004-0004-000000000004', 'Superstition',       'Stevie Wonder',    'Soul',  245),
    ('a1b2c3d4-0005-0005-0005-000000000005', 'Lose Yourself',      'Eminem',           'Hip-Hop', 326),
    ('a1b2c3d4-0006-0006-0006-000000000006', 'Rolling in the Deep','Adele',            'Pop',   228),
    ('a1b2c3d4-0007-0007-0007-000000000007', 'Smells Like Teen Spirit', 'Nirvana',     'Rock',  301),
    ('a1b2c3d4-0008-0008-0008-000000000008', 'Billie Jean',        'Michael Jackson',  'Pop',   294);

MERGE INTO playlists (id, name, description) KEY(id) VALUES
    ('b1b2c3d4-0001-0001-0001-000000000001', 'Rock Classics',   'Best rock songs ever'),
    ('b1b2c3d4-0002-0002-0002-000000000002', 'Pop Hits',        'Top pop songs');

MERGE INTO playlist_entries (position, playlist_id, song_id) KEY(playlist_id, song_id) VALUES
    (0, 'b1b2c3d4-0001-0001-0001-000000000001', 'a1b2c3d4-0001-0001-0001-000000000001'),
    (1, 'b1b2c3d4-0001-0001-0001-000000000001', 'a1b2c3d4-0002-0002-0002-000000000002'),
    (2, 'b1b2c3d4-0001-0001-0001-000000000001', 'a1b2c3d4-0007-0007-0007-000000000007'),
    (0, 'b1b2c3d4-0002-0002-0002-000000000002', 'a1b2c3d4-0003-0003-0003-000000000003'),
    (1, 'b1b2c3d4-0002-0002-0002-000000000002', 'a1b2c3d4-0006-0006-0006-000000000006'),
    (2, 'b1b2c3d4-0002-0002-0002-000000000002', 'a1b2c3d4-0008-0008-0008-000000000008');