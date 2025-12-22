CREATE TABLE IF NOT EXISTS contents(
    id SERIAL PRIMARY KEY,
    section_id INT NOT NULL REFERENCES sections(id),
    name VARCHAR(255) NOT NULL

);