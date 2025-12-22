CREATE TABLE IF NOT EXISTS permissions (
    id SERIAL PRIMARY KEY,
    permission VARCHAR(255) NOT NULL,
    role_id INT NOT NULL REFERENCES roles(id)
);
