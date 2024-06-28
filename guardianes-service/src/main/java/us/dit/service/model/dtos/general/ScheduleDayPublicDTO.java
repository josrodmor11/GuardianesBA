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

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.core.Relation;
import us.dit.service.model.entities.Doctor;
import us.dit.service.model.entities.ScheduleDay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This DTO represents the information related to a {@link ScheduleDay} exposed
 * through the REST interface
 *
 * @author miggoncan
 */
@Data
@Relation(value = "scheduleDay", collectionRelation = "scheduleDays")
@Slf4j
public class ScheduleDayPublicDTO implements Comparable<ScheduleDayPublicDTO> {
    private Integer day;
    private Boolean isWorkingDay;
    private List<DoctorPublicDTO> cycle;
    private List<DoctorPublicDTO> shifts;
    private List<DoctorPublicDTO> consultations;

    public ScheduleDayPublicDTO(ScheduleDay scheduleDay) {
        log.info("Creating a ScheduleDayPublicDTO from the ScheduleDay: " + scheduleDay);
        if (scheduleDay != null) {
            this.day = scheduleDay.getDay();
            this.isWorkingDay = scheduleDay.getIsWorkingDay();
            this.cycle = this.toListDoctorsDTO(scheduleDay.getCycle());
            this.shifts = this.toListDoctorsDTO(scheduleDay.getShifts());
            this.consultations = this.toListDoctorsDTO(scheduleDay.getConsultations());
        }
        log.info("The crated ScheduleDayPublicDTO is: " + this);
    }

    public ScheduleDayPublicDTO() {
    }

    @Override
    public int compareTo(ScheduleDayPublicDTO scheduleDay) {
        if (scheduleDay == null) {
            return -1;
        }

        int result = 0;
        Integer scheduleDayDay = scheduleDay.getDay();
        if (scheduleDayDay == null) {
            if (this.day == null) {
                result = 0;
            } else {
                result = -1;
            }
        } else if (this.day == null) {
            result = 1;
        } else {
            result = this.day - scheduleDayDay;
        }

        return result;
    }

    public ScheduleDay toScheduleDay() {
        log.info("Converting to a ScheduleDay this ScheduleDayPublicDTO: " + this);
        ScheduleDay scheduleDay = new ScheduleDay();
        scheduleDay.setDay(this.day);
        scheduleDay.setIsWorkingDay(this.isWorkingDay);
        scheduleDay.setCycle(this.toListDoctors(this.cycle));
        scheduleDay.setShifts(this.toListDoctors(this.shifts));
        scheduleDay.setConsultations(this.toListDoctors(this.consultations));
        log.info("The converted ScheduleDay is: " + scheduleDay);
        return scheduleDay;
    }

    private Set<Doctor> toSetDoctors(Set<DoctorPublicDTO> doctorDTOs) {
        log.info("Converting to a Set of Doctors the set: " + doctorDTOs);
        Set<Doctor> doctors = new HashSet<>();
        if (doctorDTOs != null) {
            for (DoctorPublicDTO doctorDTO : doctorDTOs) {
                doctors.add(doctorDTO.toDoctor());
            }
        }
        log.info("The converted set is: " + doctors);
        return doctors;
    }

    private Set<DoctorPublicDTO> toSetDoctorsDTO(Set<Doctor> doctors) {
        log.info("Converting to a Set of DoctorPublicDTOss the set: " + doctors);
        Set<DoctorPublicDTO> doctorDTOs = new HashSet<>();
        if (doctors != null) {
            for (Doctor doctor : doctors) {
                doctorDTOs.add(new DoctorPublicDTO(doctor));
            }
        }
        log.info("The converted set is: " + doctorDTOs);
        return doctorDTOs;
    }

    private List<Doctor> toListDoctors(List<DoctorPublicDTO> doctorDTOs) {
        log.info("Converting to a Set of Doctors the set: " + doctorDTOs);
        List<Doctor> doctors = new ArrayList<>();
        if (doctorDTOs != null) {
            for (DoctorPublicDTO doctorDTO : doctorDTOs) {
                doctors.add(doctorDTO.toDoctor());
            }
        }
        log.info("The converted set is: " + doctors);
        return doctors;
    }

    private List<DoctorPublicDTO> toListDoctorsDTO(List<Doctor> doctors) {
        log.info("Converting to a Set of DoctorPublicDTOss the set: " + doctors);
        List<DoctorPublicDTO> doctorDTOs = new ArrayList<>();
        if (doctors != null) {
            for (Doctor doctor : doctors) {
                doctorDTOs.add(new DoctorPublicDTO(doctor));
            }
        }
        log.info("The converted set is: " + doctorDTOs);
        return doctorDTOs;
    }
}