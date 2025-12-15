CREATE TABLE IF NOT EXISTS users_courses(
    users_id INT NOT NULL,
    courses_id INT NOT NULL,
    PRIMARY KEY(users_id,courses_id),

    CONSTRAINT fk_users_courses_user
        FOREIGN KEY(users_id) REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_users_courses_course
        FOREIGN KEY(courses_id) REFERENCES courses(id)
        ON DELETE CASCADE

);