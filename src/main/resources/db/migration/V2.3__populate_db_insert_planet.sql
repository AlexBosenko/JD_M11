INSERT INTO ticket(created_at, client_id, from_planet_id, to_planet_id)
VALUES
    ('2012-03-01'::TIMESTAMP, 1, 'MARS', 'VEN1'),
    ('2015-04-28'::TIMESTAMP, 2, 'VEN1', 'NEPT'),
    ('2021-12-31'::TIMESTAMP, 3, 'JUP4', 'NEPT'),
    ('2023-01-01'::TIMESTAMP, 4, 'SAT77', 'JUP4'),
    ('2010-07-25'::TIMESTAMP, 5, 'NEPT', 'MARS'),
    ('2016-05-22'::TIMESTAMP, 2, 'NEPT', 'VEN1'),
    ('2022-02-27'::TIMESTAMP, 3, 'NEPT', 'SAT77'),
    ('2023-09-30'::TIMESTAMP, 7, 'JUP4', 'MARS'),
    ('2016-10-16'::TIMESTAMP, 8, 'MARS', 'JUP4'),
    ('2022-11-21'::TIMESTAMP, 3, 'SAT77', 'JUP4')