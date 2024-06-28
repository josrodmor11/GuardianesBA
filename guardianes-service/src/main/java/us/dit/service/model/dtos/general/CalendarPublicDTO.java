/**
*  This file is part of GuardianesBA - Business Application for processes managing healthcare tasks planning and supervision.
*  Copyright (C) 2024  Universidad de Sevilla/Departamento de Ingeniería Telemática
*
*  GuardianesBA is free software: you can redistribute it and/or
*  modify it under the terms of the GNU General Public License as published
*  by the Free Software Foundation, either version 3 of the License, or (at
*  your option) any later version.
*
*  GuardianesBA is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
*  Public License for more details.
*
*  You should have received a copy of the GNU General Public License along
*  with GuardianesBA. If not, see <https://www.gnu.org/licenses/>.
**/
package us.dit.service.model.dtos.general;

import us.dit.service.model.entities.Calendar;
import us.dit.service.model.entities.DayConfiguration;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.core.Relation;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class represents the information related to a {@link Calendar} that is
 * exposed through the REST interface
 * 
 * @author miggoncan
 */
@Data
@Relation(value = "calendar", collectionRelation = "calendars")
@Slf4j
public class CalendarPublicDTO {
	private Integer month;
	private Integer year;
	// dayConfigurations are not mapped correctly
	private SortedSet<DayConfigurationPublicDTO> dayConfigurations;

	public CalendarPublicDTO(Calendar calendar) {
		log.info("Creating CalendarPublicDTO from Calendar: " + calendar);
		if (calendar == null) {
			log.info("The calendar is null");
		} else {
			this.month = calendar.getMonth();
			log.debug("The calendar's month is: " + this.month);
			this.year = calendar.getYear();
			log.debug("The calendar's year is: " + this.year);
			SortedSet<DayConfigurationPublicDTO> dayConfDTOs = new TreeSet<>();
			SortedSet<DayConfiguration> dayConfs = calendar.getDayConfigurations();
			log.debug("The calendar's dayConfs are: " + dayConfs);
			for (DayConfiguration dayConf : dayConfs) {
				log.debug("Mapping to DayConfigurationPublicDTO the DayConfiguration: " + dayConf);
				DayConfigurationPublicDTO dayConfDTO = new DayConfigurationPublicDTO(dayConf);
				log.debug("The mapped DayConfigurationPublicDTO is: " + dayConfDTO);
				dayConfDTOs.add(dayConfDTO);
			}
			this.dayConfigurations = dayConfDTOs;
			log.debug("The set of DayConfigurationPublicDTO is: " + this.dayConfigurations);
		}
		log.info("The created CalendarPublicDTO is: " + this);
	}

	public CalendarPublicDTO() {
	}

	public Calendar toCalendar() {
		log.info("Mapping this CalendarPublicDTO to a Calendar");
		log.debug("This CalendarPublicDTO is: " + this);
		Calendar calendar = new Calendar();
		calendar.setMonth(this.month);
		log.debug("The calendar's month is: " + calendar.getMonth());
		calendar.setYear(this.year);
		log.debug("The calendar's year is: " + calendar.getYear());
		SortedSet<DayConfiguration> dayConfigs = new TreeSet<>();
		log.debug("Mapping DayConfigurationPublicDTOs to DayConfigurations: " + this.dayConfigurations);
		if (this.dayConfigurations != null) {
			for (DayConfigurationPublicDTO dayConfigDTO : this.dayConfigurations) {
				log.debug("Converting to DayConfiguration the DayConfigurationPublicDTO: " + dayConfigDTO);
				DayConfiguration dayConf = dayConfigDTO.toDayConfiguration();
				dayConf.setCalendar(calendar);
				log.debug("The converted DayConfiguration is: " + dayConf);
				dayConfigs.add(dayConf);
			}
		}
		log.debug("The resulting DayConfigurations are: " + dayConfigs);
		calendar.setDayConfigurations(dayConfigs);
		log.info("The resulting Calendar is: " + calendar);
		return calendar;
	}
}
