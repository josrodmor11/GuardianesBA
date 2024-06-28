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
package us.dit.service.model.validation.validators;

import us.dit.service.model.entities.AllowedShift;
import us.dit.service.model.entities.Doctor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * This class contains the algorithm used to make sure shift preferences do not
 * clash with one another. This is, the information in an unwantedShift cannot
 * be repeated in a mandatoryShift
 * 
 * @author miggoncan
 *
 * @param <ShiftType> It represents the contained in the shifts. For example,
 *                    they can be {@link Doctor}s or {@link AllowedShift}s
 */
@Slf4j
public class ShiftPreferencesValidator<ShiftType> {
	public boolean isValid(Set<ShiftType> unwantedShifts, Set<ShiftType> unavailableShifts, Set<ShiftType> wantedShifts,
			Set<ShiftType> mandatoryShifts) {
		log.debug("Request to validate shift preferences");
		
		// Create empty set if null
		unwantedShifts = unwantedShifts == null ? new HashSet<>() : unwantedShifts;
		log.debug("The unwantedShifts are: " + unwantedShifts);
		unavailableShifts = unavailableShifts == null ? new HashSet<>() : unavailableShifts;
		log.debug("The unavailableShifts are: " + unavailableShifts);
		wantedShifts = wantedShifts == null ? new HashSet<>() : wantedShifts;
		log.debug("The wantedShifts are: " + wantedShifts);
		mandatoryShifts = mandatoryShifts == null ? new HashSet<>() : mandatoryShifts;
		log.debug("The mandatoryShifts are: " + mandatoryShifts);

		// Using the stream api to check intersections between the sets
		boolean shiftsIntersect = unwantedShifts.stream().anyMatch(unavailableShifts::contains)
				|| unwantedShifts.stream().anyMatch(wantedShifts::contains)
				|| unwantedShifts.stream().anyMatch(mandatoryShifts::contains)
				|| unavailableShifts.stream().anyMatch(wantedShifts::contains)
				|| unavailableShifts.stream().anyMatch(mandatoryShifts::contains)
				|| wantedShifts.stream().anyMatch(mandatoryShifts::contains);
		log.debug("The shift preferences are valid: " + !shiftsIntersect);
		
		return !shiftsIntersect;
	}
}
