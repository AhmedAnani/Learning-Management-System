CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY   ,
    first_name VARCHAR(255) NOT NULL,
    second_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(255) UNIQUE NOT NULL,
    otp VARCHAR(6) ,
    otp_expiration TIMESTAMP,
    verified BOOLEAN NOT NULL DEFAULT FALSE,
    password VARCHAR(255)  NOT NULL
);