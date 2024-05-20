package us.dit.service.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import us.dit.service.services.CalendarTaskService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/guardianes")
public class ScheduleController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private CalendarTaskService calendarTaskService;

    /**
     * Este método mostrará el menú de planificación
     *
     * @param session objeto que maneja la sesion HTTP
     * @return El html de la planificación
     */
    @GetMapping("/guardianes/schedules")
    public String menu(HttpSession session) {
        logger.info("Devolvemos el html de la planificacion");
        return "schedules";
    }


}
