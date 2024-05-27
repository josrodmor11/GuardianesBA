package us.dit.service.model.dtos.scheduler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import us.dit.service.model.entities.Doctor;
import us.dit.service.model.entities.ScheduleDay;

import java.util.ArrayList;
import java.util.List;

/**
 * This DTO will represent the information related to a {@link ScheduleDay}
 * exposed to the scheduler
 *
 * @author miggoncan
 */
@Data
@Slf4j
public class ScheduleDaySchedulerDTO implements Comparable<ScheduleDaySchedulerDTO> {
    private Integer day;
    private Boolean isWorkingDay;
    private List<DoctorSchedulerDTO> cycle;
    private List<DoctorSchedulerDTO> shifts;
    private List<DoctorSchedulerDTO> consultations;

    @Override
    public int compareTo(ScheduleDaySchedulerDTO scheduleDay) {
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
        log.info("Converting to a ScheduleDay this ScheduleDaySchedulerDTO: " + this);
        ScheduleDay scheduleDay = new ScheduleDay();
        scheduleDay.setDay(this.day);
        scheduleDay.setIsWorkingDay(this.isWorkingDay);
        scheduleDay.setCycle(this.toListDoctors(this.cycle));
        scheduleDay.setShifts(this.toListDoctors(this.shifts));
        scheduleDay.setConsultations(this.toListDoctors(this.consultations));
        log.info("The converted ScheduleDay is: " + scheduleDay);
        return scheduleDay;
    }

    private List<Doctor> toListDoctors(List<DoctorSchedulerDTO> doctorDTOs) {
        log.info("Converting to a List of Doctors the set: " + doctorDTOs);
        List<Doctor> doctors = new ArrayList<>();
        if (doctorDTOs != null) {
            for (DoctorSchedulerDTO doctorDTO : doctorDTOs) {
                doctors.add(doctorDTO.toDoctor());
            }
        }
        log.info("The converted set is: " + doctors);
        return doctors;
    }

}