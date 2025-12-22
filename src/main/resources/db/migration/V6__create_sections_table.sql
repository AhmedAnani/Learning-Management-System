CREATE TABLE IF NOT EXISTS sections(
    id SERIAL PRIMARY KEY,
    course_id INT NOT NULL REFERENCES courses(id),
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);