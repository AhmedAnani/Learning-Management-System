CREATE TABLE IF NOT EXISTS sections(
    id SERIAL PRIMARY KEY,
    courses_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,

    CONSTRAINT fk_contents_course
        FOREIGN KEY (courses_id) REFERENCES courses(id)
        ON DELETE CASCADE
);