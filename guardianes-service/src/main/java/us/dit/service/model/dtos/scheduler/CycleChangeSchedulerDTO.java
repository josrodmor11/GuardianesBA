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

import us.dit.service.model.entities.CycleChange;
import us.dit.service.model.entities.Doctor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * This DTO represents the information related to a {@link CycleChange} exposed
 * to the scheduler
 * 
 * @author miggoncan
 */
@Data
@Slf4j
public class CycleChangeSchedulerDTO {
	private DoctorSchedulerDTO cycleGiver;
	private DoctorSchedulerDTO cycleReceiver;
	
	public CycleChangeSchedulerDTO(CycleChange cycleChange) {
		log.info("Creating a CycleChangeSchedulerDTO from the CycleChange: " + cycleChange);
		if (cycleChange != null) {
			Doctor cycleGiver = cycleChange.getCycleGiver();
			log.debug("The cycleGiver is: " + cycleGiver);
			this.cycleGiver = new DoctorSchedulerDTO(cycleGiver);
			Doctor cycleReceiver = cycleChange.getCycleReceiver();
			log.debug("The cycleReceiver is: " + cycleReceiver);
			this.cycleReceiver = new DoctorSchedulerDTO(cycleReceiver);
		}
		log.info("The created CycleChangeSchedulerDTO is: " + this);
	}
	
	public CycleChangeSchedulerDTO() {
	}
}
