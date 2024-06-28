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

import java.util.List;

/**
 * Esta clase representa los festivos que recibimos del cliente en una peticion HTTP
 *
 * @author Jose Carlos Rodríguez Morón
 * @version 1.0
 * @date Julio 2024
 */
public class Festivos {
    private List<String> festivos;

    public List<String> getFestivos() {
        return festivos;
    }

    public void setFestivos(List<String> festivos) {
        this.festivos = festivos;
    }

    @Override
    public String toString() {
        return "FestivosRequest{" +
                "festivos=" + this.getFestivos() +
                '}';
    }
}
