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

import us.dit.service.model.entities.CycleChange;
import us.dit.service.model.entities.Doctor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.core.Relation;

/**
 * This DTO represents the information related to a {@link CycleChange} exposed
 * through the REST interface
 * 
 * @author miggoncan
 */
@Data
@Relation(value = "cycleChange", collectionRelation = "cycleChanges")
@Slf4j
public class CycleChangePublicDTO {
	// TODO Should these doctors have to be represented as embedded resources when serializing?
	private DoctorPublicDTO cycleGiver;
	private DoctorPublicDTO cycleReceiver;
	
	public CycleChangePublicDTO(CycleChange cycleChange) {
		log.info("Creating a CycleChangePublicDTO from the CycleChange: " + cycleChange);
		if (cycleChange != null) {
			Doctor cycleGiver = cycleChange.getCycleGiver();
			log.debug("The cycleGiver is: " + cycleGiver);
			this.cycleGiver = new DoctorPublicDTO(cycleGiver);
			Doctor cycleReceiver = cycleChange.getCycleReceiver();
			log.debug("The cycleReceiver is: " + cycleReceiver);
			this.cycleReceiver = new DoctorPublicDTO(cycleReceiver);
		}
		log.info("The created CycleChangePublicDTO is: " + this);
	}
	
	public CycleChangePublicDTO() {
	}

	public CycleChange toCycleChange() {
		log.info("Creating a CycleChange from this CycleChangePublicDTO: " + this);
		CycleChange cycleChange = new CycleChange();
		log.debug("This cycleGiver is: " + this.cycleGiver);
		cycleChange.setCycleGiver(this.cycleGiver.toDoctor());
		log.debug("The cycleChange cycleGiver is: " + cycleChange.getCycleGiver());
		log.debug("This cycleReceiver is: " + this.cycleReceiver);
		cycleChange.setCycleReceiver(this.cycleReceiver.toDoctor());
		log.debug("The cycleChange cycleReceiver is: " + cycleChange.getCycleReceiver());
		log.info("The created CycleChange is: " + cycleChange);
		return cycleChange;
	}
}
