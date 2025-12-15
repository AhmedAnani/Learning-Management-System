CREATE TABLE IF NOT EXISTS permissions (
    id SERIAL PRIMARY KEY,
    permission VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,

    CONSTRAINT fk_permission_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
        ON DELETE CASCADE,

    CONSTRAINT uq_permission_per_role
        UNIQUE (permission, role_id)
);
