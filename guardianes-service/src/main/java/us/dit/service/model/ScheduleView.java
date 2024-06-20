package us.dit.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;
import us.dit.service.model.entities.ScheduleDay;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

@Data
@Relation(value = "schedule", collectionRelation = "schedules")
public class ScheduleView {
    private Integer month;
    private Integer year;
    private String status;
    private SortedSet<ScheduleDay> days;
    /**
     * This attribute will not be serialized. Its main purpose is to provide the
     * thymeleaf templates an easy way of evaluating the schedules's state.
     * <p>
     * For example, if this map contains an entry for the key "confirm", it means
     * this schedule can be confirmed.
     */
    @JsonIgnore
    private Map<String, String> links;
    /**
     * This attribute will not be serialized. Its main purpose is to provide the
     * thymeleaf templates an easy way of representing the schedule of a certain
     * month.
     * <p>
     * The outer list will contain as many elements as weeks has the month. The
     * inner lists will contain exactly seven Schedule days.
     * <p>
     * Note that scheduleDats not belongin to this month may need to be inserted if
     * the first day of this month is not a Monday
     */
    @JsonIgnore
    private List<List<ScheduleDay>> weeks;


}
