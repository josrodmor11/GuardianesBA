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

import us.dit.service.model.entities.CycleChange;
import us.dit.service.model.entities.Doctor;
import us.dit.service.model.validation.annotations.ValidCycleChange;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This validator is used to make sure the giver and receiver {@link Doctor}s of
 * a {@link CycleChange} are different.
 * 
 * Particularly, this validator checks that both {@link Doctor}s are not null,
 * their ids are not null either, and the ids are different from one another
 * 
 * @author miggoncan
 */
@Slf4j
public class CycleChangeValidator implements ConstraintValidator<ValidCycleChange, CycleChange> {

	@Override
	public boolean isValid(CycleChange value, ConstraintValidatorContext context) {
		log.debug("Request to validate CycleChange: " + value);
		if (value == null) {
			log.debug("Cycle change is null, so it is valid");
			return true;
		}

		Doctor giver = value.getCycleGiver();
		Doctor receiver = value.getCycleReceiver();

		if (giver == null || receiver == null) {
			log.debug("Either the giver Doctor or the receiver Doctor are null, so the CycleChange is invalid");
			return false;
		}

		Long idGiver = giver.getId();
		Long idReceiver = receiver.getId();
		boolean isValid = idGiver != null && idReceiver != null && !idGiver.equals(idReceiver);
		log.debug("After comparing the ids, the CycleChange is valid: " + isValid);
		return isValid;
	}

}
