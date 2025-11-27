CREATE TABLE IF NOT EXISTS articles(
    id SERIAL PRIMARY KEY,
    contents_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    creation_time DATE NOT NULL,

    CONSTRAINT fk_articles_content
        FOREIGN KEY (contents_id) REFERENCES contents(id)
        ON DELETE CASCADE
);