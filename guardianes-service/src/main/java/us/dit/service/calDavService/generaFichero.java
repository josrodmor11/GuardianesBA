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

package us.dit.service.calDavService;

import lombok.extern.slf4j.Slf4j;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.validate.ValidationException;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Clase que crea el fichero
 *
 * @author carcohcal
 * @date 12 feb. 2022
 */

@Service
@Slf4j
public class generaFichero {

    /**
     * @param calendario
     * @param nomFich
     * @throws ValidationException
     * @throws IOException
     * @author
     * @version
     */
    public static void generarFichero(Calendar calendario, String nomFich) throws ValidationException, IOException {

        FileOutputStream fout;
        CalendarOutputter outputter = new CalendarOutputter();
        outputter.setValidating(false);
        fout = new FileOutputStream(nomFich);
        outputter.output(calendario, fout);
        log.info("Fichero " + nomFich + " creado");
        fout.close();


    }


}
