package az.project.digacc.repository;

import az.project.digacc.entity.ContactFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFormRepository extends JpaRepository<ContactFormEntity, Long> {
}
