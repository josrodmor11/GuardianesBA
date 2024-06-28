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

import us.dit.service.model.entities.AllowedShift;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.core.Relation;

/**
 * This DTO represents the information related to an {@link AllowedShift}
 * exposed through the REST interface
 * 
 * @author miggoncan
 */
@Data
@Relation(value = "allowedShift", collectionRelation = "allowedShifts")
@Slf4j
public class AllowedShiftPublicDTO {
	private Integer id;
	private String shift;

	public AllowedShiftPublicDTO(AllowedShift allowedShift) {
		log.info("Creating an AllowedShiftPublicDTO from the AllowedShift: " + allowedShift);
		if (allowedShift != null) {
			this.id = allowedShift.getId();
			log.debug("The AllowedShift id is: " + this.id);
			this.shift = allowedShift.getShift();
			log.debug("The AllowedShift shif is: " + this.shift);
		}
		log.info("The created AllowedShiftPublicDTO is: " + this);
	}

	public AllowedShiftPublicDTO() {
	}

	public AllowedShift toAllowedShift() {
		log.info("Creating an AllowedShift from this AllowedShiftPublicDTO: " + this);
		AllowedShift allowedShift = new AllowedShift();
		allowedShift.setId(this.id);
		log.debug("The AllowedShift id is: " + allowedShift.getId());
		allowedShift.setShift(this.shift);
		log.debug("The AllowedShift shift is: " + allowedShift.getShift());
		log.info("The created AllowedShift is: " + allowedShift);
		return allowedShift;
	}
}
