CREATE TABLE IF NOT EXISTS videos(
    id SERIAL PRIMARY KEY,
    contents_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    path VARCHAR(255) NOT NULL,
    creation_time DATE NOT NULL,

    CONSTRAINT fk_videos_content
        FOREIGN KEY (contents_id) REFERENCES contents(id)
        ON DELETE CASCADE


);