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
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * This DTO represents a summarized version of a {@link Schedule}
 * 
 * @author miggoncan
 */
@Data
@Slf4j
public class ScheduleSummaryPublicDTO {
	private Integer month;
	private Integer year;
	private ScheduleStatus status;

	public ScheduleSummaryPublicDTO() {
	}

	public ScheduleSummaryPublicDTO(Schedule schedule) {
		log.info("Creating a SchedulePublicDTO from the schedule: " + schedule);
		this.month = schedule.getMonth();
		this.year = schedule.getYear();
		this.status = schedule.getStatus();
	}
}
