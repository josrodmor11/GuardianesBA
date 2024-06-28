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
package us.dit.service.model.dtos.scheduler;

import us.dit.service.model.entities.Calendar;
import us.dit.service.model.entities.DayConfiguration;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class represents the information related to a {@link Calendar} that is
 * exposed to the scheduler
 * 
 * @author miggoncan
s */
@Data
@Slf4j
public class CalendarSchedulerDTO {
	private Integer month;
	private Integer year;
	private SortedSet<DayConfigurationSchedulerDTO> dayConfigurations;
	
	public CalendarSchedulerDTO(Calendar calendar) {
		log.info("Creating CalendarSchedulerDTO from Calendar: " + calendar);
		if (calendar == null) {
			log.info("The calendar is null");
		} else {
			this.month = calendar.getMonth();
			log.debug("The calendar's month is: " + this.month);
			this.year = calendar.getYear();
			log.debug("The calendar's year is: " + this.year);
			SortedSet<DayConfigurationSchedulerDTO> dayConfDTOs = new TreeSet<>();
			SortedSet<DayConfiguration> dayConfs = calendar.getDayConfigurations();
			log.debug("The calendar's dayConfs are: " + dayConfs);
			for (DayConfiguration dayConf : dayConfs) {
				log.debug("Mapping to DayConfigurationSchedulerDTO the DayConfiguration: " + dayConf);
				DayConfigurationSchedulerDTO dayConfDTO = new DayConfigurationSchedulerDTO(dayConf);
				log.debug("The mapped DayConfigurationSchedulerDTO is: " + dayConfDTO);
				dayConfDTOs.add(dayConfDTO);
			}
			this.dayConfigurations = dayConfDTOs;
			log.debug("The set of DayConfigurationSchedulerDTO is: " + this.dayConfigurations);
		}
		log.info("The created CalendarSchedulerDTO is: " + this);
	}
	
	public CalendarSchedulerDTO() {
	}
}
