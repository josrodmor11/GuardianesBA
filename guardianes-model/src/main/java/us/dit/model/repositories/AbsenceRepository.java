package us.dit.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.dit.model.entities.Absence;

import javax.persistence.Entity;

/**
 * This interface will be used by Jpa to auto-generate a class having all the
 * CRUD operations on the {@link Absence} {@link Entity}. This operations will
 * be performed differently depending on the configured data-source. But this is
 * completely transparent to the application
 *
 * @author miggoncan
 */
@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

}
