package us.dit.service.handlers;

import com.github.caldav4j.exceptions.CalDAV4JException;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.validate.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.dit.service.calDavService.calendarioGeneral;
import us.dit.service.model.entities.Schedule;
import us.dit.service.model.entities.primarykeys.CalendarPK;
import us.dit.service.model.repositories.ScheduleRepository;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component("InformarMedicos")
public class InformDoctorsWorkItemHandler implements WorkItemHandler {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private calendarioGeneral calDAVService;

    private boolean doctorsInformed = false;

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        logger.info("Entramos en la tarea automatica Informar Medicos");
        String idPlanificacionValidada = (String) workItem.getParameter("idPlanificacionValida");
        logger.info("La planficacion valida es " + idPlanificacionValidada);
        String[] yearMonthString = idPlanificacionValidada.split("-");
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(yearMonthString[1]), Integer.parseInt(yearMonthString[0]));
        CalendarPK pk = new CalendarPK(yearMonth.getMonthValue(), yearMonth.getYear());
        Optional<Schedule> schedule = this.scheduleRepository.findById(pk);
        if (schedule.get().getStatus().equals(Schedule.ScheduleStatus.CONFIRMED)) {
            logger.info("Empezamos a informar a los medicos");
            try {
                this.calDAVService.setHorario(schedule.get());
                this.doctorsInformed = true;
            } catch (ValidationException | IOException | GeneralSecurityException | InterruptedException
                     | URISyntaxException | ParserException | CalDAV4JException | MessagingException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> params = new HashMap<>();
        logger.info("¿Los doctores han sido informados? " + this.doctorsInformed);
        params.put("procesoFinalizado", this.doctorsInformed);
        logger.info("Se completa el proceso");
        workItemManager.completeWorkItem(workItem.getId(), params);
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {

    }
}
