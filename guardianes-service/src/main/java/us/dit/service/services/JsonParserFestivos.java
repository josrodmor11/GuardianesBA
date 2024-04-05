package us.dit.service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import us.dit.service.model.Festivos;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que parsea el json recibido en un set de LocalDates
 */
public class JsonParserFestivos {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Metodo que a partir del string de JSON de festivos nos devuelve un set de LocalDate
     * que son los festivo
     *
     * @param jsonString Representa el JSON recibido del cliente
     * @return Set de los festivos
     */
    public static Set<LocalDate> parseFestivos(String jsonString) {

        Set<LocalDate> festivosSet = new HashSet<>();
        try {
            logger.info("Parseamos a LocalDate los festivos");
            ObjectMapper objectMapper = new ObjectMapper();
            Festivos festivos = objectMapper.readValue(jsonString, Festivos.class);

            for (String festivo : festivos.getFestivos()) {
                festivosSet.add(LocalDate.parse(festivo));
            }

            logger.info("Se imprimen los festivos convertidos a LocalDate: ");
            for (LocalDate fecha : festivosSet) {
                logger.info(fecha);
            }
        } catch (IOException e) {
            logger.error("Error al parsear el JSON: " + e.getMessage());
        }

        return festivosSet;
    }
}
