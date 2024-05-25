package us.dit.service.model.repositories;

import us.dit.service.model.entities.Calendar;
import us.dit.service.model.entities.primarykeys.CalendarPK;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

/**
 * This interface will be used by Jpa to auto-generate a class having all the
 * CRUD operations on the {@link Calendar} {@link Entity}. This operations will
 * be performed differently depending on the configured data-source. But this is
 * completely transparent to the application
 * 
 * @author miggoncan
 */
public interface CalendarRepository extends JpaRepository<Calendar, CalendarPK> {

}
