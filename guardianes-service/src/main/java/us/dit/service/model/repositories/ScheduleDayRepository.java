package us.dit.service.model.repositories;

import us.dit.service.model.entities.ScheduleDay;
import us.dit.service.model.entities.primarykeys.DayMonthYearPK;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

/**
 * This interface will be used by Jpa to auto-generate a class having all the
 * CRUD operations on the {@link ScheduleDay} {@link Entity}. This operations
 * will be performed differently depending on the configured data-source. But
 * this is completely transparent to the application
 * 
 * @author miggoncan
 */
public interface ScheduleDayRepository extends JpaRepository<ScheduleDay, DayMonthYearPK> {

}
