CREATE TABLE IF NOT EXISTS quizzes (
    id SERIAL PRIMARY KEY,
    content_id INT NOT NULL REFERENCES contents(id),
    name VARCHAR(255) NOT NULL,
    questions TEXT[] NOT NULL,
    creation_time DATE NOT NULL

);
