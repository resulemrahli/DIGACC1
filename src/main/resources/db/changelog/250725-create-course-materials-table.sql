--liquibase formatted sql
--changeset resul:create-course-materials-table
CREATE TABLE course_materials (
                                  id BIGSERIAL PRIMARY KEY,
                                  video_id BIGINT NOT NULL,
                                  title VARCHAR(100) NOT NULL,
                                  material_url VARCHAR(255) NOT NULL,
                                  FOREIGN KEY (video_id) REFERENCES videos(id)
);
