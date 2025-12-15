CREATE TABLE IF NOT EXISTS users_roles(
    users_id INT NOT NULL,
    roles_id INT NOT NULL,

    PRIMARY KEY (users_id, roles_id),

    CONSTRAINT fk_users_roles_user
        FOREIGN KEY (users_id) REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_users_roles_role
        FOREIGN KEY (roles_id) REFERENCES roles(id)
        ON DELETE CASCADE

);
