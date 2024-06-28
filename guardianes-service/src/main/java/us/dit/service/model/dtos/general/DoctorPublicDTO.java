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

import us.dit.service.model.entities.Absence;
import us.dit.service.model.entities.Doctor;
import us.dit.service.model.entities.Doctor.DoctorStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.core.Relation;

/**
 * This DTO will represent the information exposed through the REST interface
 * related to a {@link Doctor}
 * 
 * @author miggoncan
 */
@Data
@Relation(value = "doctor", collectionRelation = "doctors")
@Slf4j
public class DoctorPublicDTO {
	private Long id;
	private String firstName;
	private String lastNames;
	private String email;
	private DoctorStatus status;
	private AbsencePublicDTO absence;

	public DoctorPublicDTO(Doctor doctor) {
		log.info("Creating a DoctorPublicDTO from the Doctor: " + doctor);
		if (doctor != null) {
			this.id = doctor.getId();
			this.firstName = doctor.getFirstName();
			this.lastNames = doctor.getLastNames();
			this.email = doctor.getEmail();
			this.status = doctor.getStatus();
			Absence absence = doctor.getAbsence();
			if (absence != null) {
				this.absence = new AbsencePublicDTO(absence);
			}
		}
		log.info("The created DoctorPublicDTO is: " + this);
	}

	public DoctorPublicDTO() {
	}

	public Doctor toDoctor() {
		log.info("Converting this DoctorPublicDTO to a Doctor: " + this);
		Doctor doctor = new Doctor();
		doctor.setId(this.id);
		doctor.setFirstName(this.firstName);
		doctor.setLastNames(this.lastNames);
		doctor.setEmail(this.email);
		doctor.setStatus(this.status);
		if (this.absence != null) {
			doctor.setAbsence(this.absence.toAbsence());
		}
		log.info("The converted Doctor is: " + doctor);
		return doctor;
	}

}
