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
package us.dit.service.model.entities.primarykeys;

import us.dit.service.model.entities.DayConfiguration;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * This class represents the primary key of the {@link Entity}
 * {@link DayConfiguration}
 * 
 * A {@link DayConfiguration} is uniquely identified with a day, a month and a
 * year
 * 
 * @author miggoncan
 */
@Data
public class DayMonthYearPK implements Serializable {
	private static final long serialVersionUID = 6732382410268399527L;

	private Integer day;
	private Integer month;
	private Integer year;

	public DayMonthYearPK(Integer day, Integer month, Integer year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public DayMonthYearPK() {
	}
}
