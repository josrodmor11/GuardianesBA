package us.dit.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.dit.model.entities.Rol;

import javax.persistence.Entity;
import java.util.Optional;

/**
 * This interface will be used by Jpa to auto-generate a class having all the
 * CRUD operations on the {@link Rol} {@link Entity}. This operations will be
 * performed differently depending on the configured data-source. But this is
 * completely transparent to the application
 *
 * @author carcohcal
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    public Optional<Rol> findBynombreRol(String rol);
}
