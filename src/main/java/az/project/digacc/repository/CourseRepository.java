package az.project.digacc.repository;

import az.project.digacc.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleQuery, String descQuery);}
