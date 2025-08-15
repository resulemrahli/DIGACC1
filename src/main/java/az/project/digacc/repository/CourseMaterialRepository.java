package az.project.digacc.repository;

import az.project.digacc.entity.CourseMaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterialEntity, Long> {
    List<CourseMaterialEntity> findByVideoId(Long videoId);
}
