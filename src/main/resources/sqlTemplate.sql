/**
 * Author:  NicoIsaia
 * Created: 20 mar. 2024
 */

CREATE TABLE IF NOT EXISTS movies (
    id IDENTITY PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    year NUMERIC NOT NULL,
    score NUMERIC,
    watched BOOLEAN,
);

CREATE TABLE IF NOT EXISTS people (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    dateofbirth DATE,
);

CREATE TABLE IF NOT EXISTS starred (
    movie_id NUMERIC,
    people_id NUMERIC,
    FOREIGN KEY(movie_id) REFERENCES movies(id),
    FOREIGN KEY(people_id) REFERENCES people(id),
);

CREATE TABLE IF NOT EXISTS directed (
    movie_id NUMERIC,
    people_id NUMERIC,
    FOREIGN KEY(movie_id) REFERENCES movies(id),
    FOREIGN KEY(people_id) REFERENCES people(id),
);

CREATE TABLE IF NOT EXISTS genres (
    id IDENTITY PRIMARY KEY,
    genre VARCHAR(30),
);

CREATE TABLE IF NOT EXISTS movie_genres (
    movie_id NUMERIC,
    genre_id NUMERIC,
    FOREIGN KEY(movie_id) REFERENCES movies(id),
    FOREIGN KEY(genre_id) REFERENCES genres(id),
);
