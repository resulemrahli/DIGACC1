--liquibase formatted sql
--changeset resul:create-videos-table
CREATE TABLE videos (
                        id BIGSERIAL PRIMARY KEY,
                        course_id BIGINT NOT NULL,
                        title VARCHAR(100) NOT NULL,
                        description TEXT,
                        video_url VARCHAR(255) NOT NULL,
                        FOREIGN KEY (course_id) REFERENCES courses(id)
);
