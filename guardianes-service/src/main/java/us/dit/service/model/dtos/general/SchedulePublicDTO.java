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

import us.dit.service.model.entities.Schedule;
import us.dit.service.model.entities.Schedule.ScheduleStatus;
import us.dit.service.model.entities.ScheduleDay;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.core.Relation;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This DTO will represent the information related to a {@link Schedule} exposed
 * through the REST interface
 * 
 * @author miggoncan
 */
@Data
@Relation(value = "schedule", collectionRelation = "schedules")
@Slf4j
public class SchedulePublicDTO {
	private Integer month;
	private Integer year;
	private ScheduleStatus status;
	private SortedSet<ScheduleDayPublicDTO> days;
	
	public SchedulePublicDTO(Schedule schedule) {
		log.info("Creating a SchedulePublicDTO from the schedule: " + schedule);
		if (schedule != null) {
			this.month = schedule.getMonth();
			this.year = schedule.getYear();
			this.status = schedule.getStatus();
			
			SortedSet<ScheduleDayPublicDTO> scheduleDayDTOs = new TreeSet<>();
			SortedSet<ScheduleDay> scheduleDays = schedule.getDays();
			if (scheduleDays != null) {
				for (ScheduleDay scheduleDay : scheduleDays) {
					scheduleDayDTOs.add(new ScheduleDayPublicDTO(scheduleDay));
				}
			}
			this.days = scheduleDayDTOs;
		}
		log.info("The created SchedulePublicDTO is: " + this);
	}
	
	public SchedulePublicDTO() {
	}

	public Schedule toSchedule() {
		log.info("Converting to a Schedule this ScheduleDayPublicDTO: " + this);
		Schedule schedule = new Schedule();
		schedule.setMonth(this.month);
		schedule.setYear(this.year);
		schedule.setStatus(this.status);
		
		SortedSet<ScheduleDay> scheduleDays = new TreeSet<>();
		if (this.days != null) {
			for (ScheduleDayPublicDTO scheduleDayDTO : this.days) {
				ScheduleDay scheduleDay = scheduleDayDTO.toScheduleDay();
				scheduleDay.setSchedule(schedule);
				scheduleDays.add(scheduleDay);
			}
		}
		schedule.setDays(scheduleDays);
		
		log.info("The converted Schedule is: " + schedule);
		return schedule;
	}
}
