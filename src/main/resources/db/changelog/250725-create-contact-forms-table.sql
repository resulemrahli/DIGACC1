--liquibase formatted sql
--changeset resul:create-contact-forms-table
CREATE TABLE contact_forms (
                               id BIGSERIAL PRIMARY KEY,
                               first_name VARCHAR(50) NOT NULL,
                               last_name VARCHAR(50) NOT NULL,
                               email VARCHAR(100) NOT NULL,
                               phone_number VARCHAR(20),
                               cv_url VARCHAR(255) NOT NULL,
                               submitted_at TIMESTAMP NOT NULL
);