package us.dit.service.model.entities;

import us.dit.service.model.entities.primarykeys.CalendarPK;
import us.dit.service.model.validation.annotations.ValidCalendar;
import lombok.Data;
import org.hibernate.annotations.SortNatural;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.SortedSet;

/**
 * The Calendar {@link Entity} represents a certain month o a certain year, and
 * might have some specific configuration besides the specified in
 * {@link ShiftConfiguration}.
 * 
 * Note the primary key used for this {@link Entity} is composite, so the
 * {@link IdClass} annotation is used.
 * 
 * @see DayConfiguration
 * @see CalendarPK
 * 
 * @author miggoncan
 */
@Data
@Entity
@IdClass(CalendarPK.class)
@ValidCalendar
public class Calendar {
	@Id
	@Range(min = 1, max = 12)
	private Integer month;
	@Id
	@Range(min = 1970)
	private Integer year;

	@OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
	@SortNatural
	private SortedSet<DayConfiguration> dayConfigurations;

	public Calendar(Integer month, Integer year) {
		this.month = month;
		this.year = year;
	}

	public Calendar() {
	}
	
	public void setDayConfigurations(SortedSet<DayConfiguration> dayConfigurations) {
		this.dayConfigurations = dayConfigurations;
		if (dayConfigurations != null) {
			for (DayConfiguration dayConfiguration : dayConfigurations) {
				dayConfiguration.setCalendar(this);
			}
		}
	}
}
