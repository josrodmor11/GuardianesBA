package us.dit.model.dtos.scheduler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import us.dit.model.entities.Absence;
import us.dit.model.entities.Doctor;
import us.dit.model.entities.Doctor.DoctorStatus;

import java.time.LocalDate;

/**
 * This DTO represents the information related to a {@link Doctor} that is
 * exposed to the scheduler
 *
 * @author miggoncan
 * s
 */
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
