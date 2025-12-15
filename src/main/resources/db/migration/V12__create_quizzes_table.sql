CREATE TABLE IF NOT EXISTS quizzes (
    id SERIAL PRIMARY KEY,
    contents_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    questions TEXT[] NOT NULL,
    creation_time DATE NOT NULL,

    CONSTRAINT fk_quizzes_content
        FOREIGN KEY (contents_id) REFERENCES contents(id)
        ON DELETE CASCADE
);
