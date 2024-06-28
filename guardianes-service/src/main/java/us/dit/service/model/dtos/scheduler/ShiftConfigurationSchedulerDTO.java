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

import us.dit.service.model.entities.AllowedShift;
import us.dit.service.model.entities.ShiftConfiguration;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
/**
 * This DTO represents the information related to a {@link ShiftConfiguration}
 * exposed to the scheduler
 * 
 * @author miggoncan
s */
@Data
@Slf4j
public class ShiftConfigurationSchedulerDTO {
	private Long doctorId;
	private Integer maxShifts;
	private Integer minShifts;
	private Integer numConsultations;
	private Boolean doesCycleShifts;
	private Boolean hasShiftsOnlyWhenCycleShifts;
	private Set<AllowedShiftSchedulerDTO> unwantedShifts;
	private Set<AllowedShiftSchedulerDTO> unavailableShifts;
	private Set<AllowedShiftSchedulerDTO> wantedShifts;
	private Set<AllowedShiftSchedulerDTO> mandatoryShifts;
	private Set<AllowedShiftSchedulerDTO> wantedConsultations;
	
	public ShiftConfigurationSchedulerDTO(ShiftConfiguration shiftConf) {
		log.info("Creating a ShiftConfigurationSchedulerDTO from the ShiftConfiguration: " + shiftConf);
		if (shiftConf != null) {
			this.doctorId = shiftConf.getDoctorId();
			this.maxShifts = shiftConf.getMaxShifts();
			this.minShifts = shiftConf.getMinShifts();
			this.numConsultations = shiftConf.getNumConsultations();
			this.doesCycleShifts = shiftConf.getDoesCycleShifts();
			this.hasShiftsOnlyWhenCycleShifts = shiftConf.getHasShiftsOnlyWhenCycleShifts();
			this.unwantedShifts = this.toSetAllowedShiftDTOs(shiftConf.getUnwantedShifts());
			this.unavailableShifts = this.toSetAllowedShiftDTOs(shiftConf.getUnavailableShifts());
			this.wantedShifts = this.toSetAllowedShiftDTOs(shiftConf.getWantedShifts());
			this.mandatoryShifts = this.toSetAllowedShiftDTOs(shiftConf.getMandatoryShifts());
			this.wantedConsultations = this.toSetAllowedShiftDTOs(shiftConf.getWantedConsultations());
		}
		log.info("The created ShiftConfigurationSchedulerDTO is: " + this);
	}
	
	public ShiftConfigurationSchedulerDTO() {
	}
	
	private Set<AllowedShiftSchedulerDTO> toSetAllowedShiftDTOs(Set<AllowedShift> allowedShifts) {
		log.info("Creating a Set of AllowedShiftSchedulerDTO from the set: " + allowedShifts);
		Set<AllowedShiftSchedulerDTO> allowedShiftDTOs = new HashSet<>();
		if (allowedShifts != null) {
			for (AllowedShift allowedShift : allowedShifts) {
				allowedShiftDTOs.add(new AllowedShiftSchedulerDTO(allowedShift));
			}
		}
		log.info("The created Set is: " + allowedShiftDTOs);
		return allowedShiftDTOs;
	}
}
