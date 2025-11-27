CREATE TABLE IF NOT EXISTS roles_permissions(
    roles_id INT NOT NULL,
    permissions_id INT NOT NULL,

    PRIMARY KEY(roles_id,permissions_id),

    CONSTRAINT fk_roles_permissions_role
            FOREIGN KEY (roles_id) REFERENCES roles(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_roles_permissions_permission
        FOREIGN KEY (permissions_id) REFERENCES permissions(id)
        ON DELETE CASCADE

);