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
package us.dit.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;
import us.dit.service.model.entities.ScheduleDay;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;
/**
 *
 * @author Jose Carlos Rodríguez Morón
 * @version 1.0
 * @date Julio 2024
 */
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
