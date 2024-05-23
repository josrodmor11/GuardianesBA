package us.dit.model.dtos.general;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import us.dit.model.entities.Schedule;
import us.dit.model.entities.Schedule.ScheduleStatus;

/**
 * This DTO represents a summarized version of a {@link Schedule}
 *
 * @author miggoncan
 */
@Data
@Slf4j
public class ScheduleSummaryPublicDTO {
    private Integer month;
    private Integer year;
    private ScheduleStatus status;

    public ScheduleSummaryPublicDTO() {
    }

    public ScheduleSummaryPublicDTO(Schedule schedule) {
        log.info("Creating a SchedulePublicDTO from the schedule: " + schedule);
        this.month = schedule.getMonth();
        this.year = schedule.getYear();
        this.status = schedule.getStatus();
    }
}
