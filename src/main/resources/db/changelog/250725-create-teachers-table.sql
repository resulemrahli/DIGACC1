--liquibase formatted sql
--changeset resul:create-teachers-table
CREATE TABLE teachers (
                          id BIGSERIAL PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          bio TEXT,
                          photo_url VARCHAR(255)
);