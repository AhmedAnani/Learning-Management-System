CREATE TABLE IF NOT EXISTS articles(
    id SERIAL PRIMARY KEY,
    content_id INT NOT NULL REFERENCES contents(id),
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    creation_time DATE NOT NULL

);