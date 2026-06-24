MERGE INTO songs (id, title, artist, genre, duration_seconds) KEY(id) VALUES
    ('a1b2c3d4-0001-0001-0001-000000000001', 'Bohemian Rhapsody',       'Queen',             'Rock',    354),
    ('a1b2c3d4-0002-0002-0002-000000000002', 'Hotel California',        'Eagles',            'Rock',    391),
    ('a1b2c3d4-0003-0003-0003-000000000003', 'Blinding Lights',         'The Weeknd',        'Pop',     200),
    ('a1b2c3d4-0004-0004-0004-000000000004', 'Superstition',            'Stevie Wonder',     'Soul',    245),
    ('a1b2c3d4-0005-0005-0005-000000000005', 'Lose Yourself',           'Eminem',            'Hip-Hop', 326),
    ('a1b2c3d4-0006-0006-0006-000000000006', 'Rolling in the Deep',     'Adele',             'Pop',     228),
    ('a1b2c3d4-0007-0007-0007-000000000007', 'Smells Like Teen Spirit', 'Nirvana',           'Rock',    301),
    ('a1b2c3d4-0008-0008-0008-000000000008', 'Billie Jean',             'Michael Jackson',   'Pop',     294),
    ('a1b2c3d4-0009-0009-0009-000000000009', 'We Will Rock You',        'Queen',             'Rock',    122),
    ('a1b2c3d4-0010-0010-0010-000000000010', 'Somebody That I Used to Know', 'Gotye',        'Pop',     244),
    ('a1b2c3d4-0011-0011-0011-000000000011', 'Rap God',                 'Eminem',            'Hip-Hop', 363),
    ('a1b2c3d4-0012-0012-0012-000000000012', 'Come Together',           'The Beatles',       'Rock',    259),
    ('a1b2c3d4-0013-0013-0013-000000000013', 'Shape of You',            'Ed Sheeran',        'Pop',     234),
    ('a1b2c3d4-0014-0014-0014-000000000014', 'Stayin Alive',            'Bee Gees',          'Disco',   245),
    ('a1b2c3d4-0015-0015-0015-000000000015', 'Good as Hell',            'Lizzo',             'Pop',     159),
    ('a1b2c3d4-0016-0016-0016-000000000016', 'Under Pressure',          'Queen',             'Rock',    248),
    ('a1b2c3d4-0017-0017-0017-000000000017', 'Stan',                    'Eminem',            'Hip-Hop', 404),
    ('a1b2c3d4-0018-0018-0018-000000000018', 'Hello',                   'Adele',             'Pop',     295),
    ('a1b2c3d4-0019-0019-0019-000000000019', 'In Bloom',                'Nirvana',           'Rock',    255),
    ('a1b2c3d4-0020-0020-0020-000000000020', 'Thriller',                'Michael Jackson',   'Pop',     358),
    ('a1b2c3d4-0021-0021-0021-000000000021', 'Higher Ground',           'Stevie Wonder',     'Soul',    210),
    ('a1b2c3d4-0022-0022-0022-000000000022', 'Let Me Love You',         'The Weeknd',        'R&B',     207),
    ('a1b2c3d4-0023-0023-0023-000000000023', 'Hey Jude',                'The Beatles',       'Rock',    431),
    ('a1b2c3d4-0024-0024-0024-000000000024', 'Perfect',                 'Ed Sheeran',        'Pop',     263),
    ('a1b2c3d4-0025-0025-0025-000000000025', 'Lose Control',            'Missy Elliott',     'Hip-Hop', 199),
    ('a1b2c3d4-0026-0026-0026-000000000026', 'Don t Stop Me Now',       'Queen',             'Rock',    209),
    ('a1b2c3d4-0027-0027-0027-000000000027', 'Someone Like You',        'Adele',             'Pop',     285),
    ('a1b2c3d4-0028-0028-0028-000000000028', 'Lithium',                 'Nirvana',           'Rock',    257),
    ('a1b2c3d4-0029-0029-0029-000000000029', 'Starboy',                 'The Weeknd',        'R&B',     230),
    ('a1b2c3d4-0030-0030-0030-000000000030', 'Beat It',                 'Michael Jackson',   'Pop',     258);

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