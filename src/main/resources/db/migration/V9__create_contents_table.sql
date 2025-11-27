CREATE TABLE IF NOT EXISTS contents(
    id SERIAL PRIMARY KEY,
    sections_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,

    CONSTRAINT fk_contents_section
        FOREIGN KEY (sections_id) REFERENCES sections(id)
        ON DELETE CASCADE
);