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
package us.dit.service.model.entities;

import java.time.LocalDate;

/**
 * This class will allow handling days of a month independently of the
 * underlying class. It also implements to method used to compare them
 * 
 * @author miggoncan
 */
public abstract class AbstractDay implements Comparable<AbstractDay> {
	/**
	 * @return The day of the month [1, 31]
	 */
	public abstract Integer getDay();

	/**
	 * @return The month of a year [1, 12]
	 */
	public abstract Integer getMonth();
	
	/**
	 * @return A year since The Year [1970, inf)
	 */
	public abstract Integer getYear();
	
	public int compareTo(AbstractDay day) {
		// Null elements are positioned at the end
		if (day == null) {
			return -1;
		}
		
		Integer myDay = this.getDay();
		Integer myMonth = this.getMonth();
		Integer myYear = this.getYear();
		Integer otherDay = day.getDay();
		Integer otherMonth = day.getMonth();
		Integer otherYear = day.getYear();
		
		boolean anyOfMineIsNull = myDay == null || myMonth == null || myYear == null;
		boolean anyOfOtherIsNull = otherDay == null || otherMonth == null || otherYear == null;
		
		int result = 0;
		if (anyOfMineIsNull) {
			if (anyOfOtherIsNull) {
				result = 0;
			} else {
				result = 1;
			}
		} else if (anyOfOtherIsNull) {
			result = -1;
		} else {
			LocalDate myDate = LocalDate.of(myYear, myMonth, myDay);
			LocalDate otherDate = LocalDate.of(otherYear, otherMonth, otherDay);
			
			result = myDate.compareTo(otherDate);
		}
		
		return result;
	}
}
