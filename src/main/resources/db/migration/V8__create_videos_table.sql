CREATE TABLE IF NOT EXISTS videos(
    id SERIAL PRIMARY KEY,
    content_id INT NOT NULL REFERENCES contents(id),
    name VARCHAR(255) NOT NULL,
    path VARCHAR(255) NOT NULL,
    creation_time DATE NOT NULL
);