CREATE TABLE IF NOT EXISTS users_courses(
    user_id INT NOT NULL REFERENCES users(id),
    course_id INT NOT NULL REFERENCES courses(id),
    PRIMARY KEY(user_id,course_id)

);