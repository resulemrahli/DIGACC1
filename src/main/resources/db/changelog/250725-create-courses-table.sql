--liquibase formatted sql
--changeset resul:create-courses-table
CREATE TABLE courses (
                         id BIGSERIAL PRIMARY KEY,
                         title VARCHAR(100) NOT NULL,
                         description TEXT,
                         created_at TIMESTAMP NOT NULL
);