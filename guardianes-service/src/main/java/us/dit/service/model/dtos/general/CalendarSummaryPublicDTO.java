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
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.core.Relation;

/**
 * This DTO represents a summarized version of a {@link Calendar}
 * @author miggoncan
 */
@Data
@Relation(value = "calendar", collectionRelation = "calendars")
@Slf4j
public class CalendarSummaryPublicDTO {
	private Integer month;
	private Integer year;
	
	public CalendarSummaryPublicDTO() {}
	
	public CalendarSummaryPublicDTO(Calendar calendar) {
		log.info("Creating a CalendarSummaryPublicDTO from the calendar: " + calendar);
		this.month = calendar.getMonth();
		this.year = calendar.getYear();
		log.info("The created summary is: " + this);
	}
}
