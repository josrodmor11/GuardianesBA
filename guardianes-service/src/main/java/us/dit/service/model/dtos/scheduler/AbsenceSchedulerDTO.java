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
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import us.dit.service.model.entities.Absence;

import java.time.LocalDate;

/**
 * This DTO will represent the information related to an {@link Absence} exposed
 * to the scheduler
 *
 * @author miggoncan
 */
@Data
@Slf4j
public class AbsenceSchedulerDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // ISO Format
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate start;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // ISO Format
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate end;

    public AbsenceSchedulerDTO(Absence absence) {
        log.info("Creating an AbsenceSchedulerDTO from the Absence: " + absence);
        if (absence != null) {
            this.start = absence.getStartDate();
            log.debug("The start date of the Absence is: " + this.start);
            this.end = absence.getEndDate();
            log.debug("The end date of the Absence is : " + this.end);
        }
        log.info("The created AbsenceSchedulerDTO is: " + this);
    }

    public AbsenceSchedulerDTO() {
    }

    public Absence toAbsence() {
        log.info("Creating an Absence from this AbsenceSchedulerDTO: " + this);
        Absence absence = new Absence(this.start, this.end);
        log.info("The created Absence is: " + absence);
        return absence;
    }
}
