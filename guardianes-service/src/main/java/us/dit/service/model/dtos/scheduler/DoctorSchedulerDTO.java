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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import us.dit.service.model.entities.Absence;
import us.dit.service.model.entities.Doctor;
import us.dit.service.model.entities.Doctor.DoctorStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * This DTO represents the information related to a {@link Doctor} that is
 * exposed to the scheduler
 * 
 * @author miggoncan
s */
@Data
@Slf4j
public class DoctorSchedulerDTO {
	private Long id;
	private DoctorStatus status;
	private AbsenceSchedulerDTO absence;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // ISO Format
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate startDate;
	
	public DoctorSchedulerDTO(Doctor doctor) {
		log.info("Creating a DoctorSchedulerDTO from the Doctor: " + doctor);
		if (doctor != null) {
			this.id = doctor.getId();
			this.status = doctor.getStatus();
			Absence absence = doctor.getAbsence();
			if (absence != null) {
				this.absence = new AbsenceSchedulerDTO(absence);
			}
			this.startDate = doctor.getStartDate();
		}
		log.info("The created DoctorSchedulerDTO is: " + this);
	}
	
	public DoctorSchedulerDTO() {
	}

	public Doctor toDoctor() {
		log.info("Converting this DoctorSchedulerDTO to a Doctor: " + this);
		Doctor doctor = new Doctor();
		doctor.setId(this.id);
		doctor.setStatus(this.status);
		if (this.absence != null) {
			doctor.setAbsence(this.absence.toAbsence());
		}
		log.info("The converted Doctor is: " + doctor);
		return doctor;
	}
}
