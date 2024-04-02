package us.dit.service.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.dit.service.controllers.SchedulerHandler;
import us.dit.service.dao.CalendarioRepository;
import us.dit.service.model.Calendario;
import us.dit.service.model.entities.Calendar;
import us.dit.service.model.entities.Schedule;
import us.dit.service.model.entities.Schedule.ScheduleStatus;

import us.dit.service.model.entities.primarykeys.CalendarPK;
import us.dit.service.model.repositories.ScheduleRepository;
import us.dit.service.model.repositories.CalendarRepository;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component("GenerarPlanificacion")
public class GenerateScheduleWorkItemHandler implements WorkItemHandler {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    SchedulerHandler schedulerHandler;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private CalendarioRepository calendarioRepository;

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        String idCalendarioFestivos = (String) workItem.getParameter("IdCalendarioFestivos");
        //Representa los datos de base de datos que se deberían coger, los médicos,
        // elecciones de las preferencias de los médicos, etc...
        String dataBase = (String) workItem.getParameter("DataBase");

        logger.info("Entramos en GenerateSchedulerWorkItemHandler");
        //Obtengo el calendario mio para poder obtener el calendar de miguel angel, ya que en
        // mi proyecto la planificacion es del mes siguiente al actual
        Optional<Calendario> calendario = this.calendarioRepository.findById(Integer.parseInt(idCalendarioFestivos));
        int year = 0;
        int month = 0;

        if(calendario.isPresent()) {
            year = calendario.get().getFestivos().iterator().next().getYear();
            month = calendario.get().getFestivos().iterator().next().getMonthValue();
        }
        //Asi el yearMonth construido es del mes siguiente y es el obtenido de la tarea Establecer festivos
        YearMonth yearMonth = YearMonth.of(year,month);

        logger.info("Request received to generate schedule for: " + yearMonth);
        //Y ademas como construimos en la tarea establecer festivos el calendario de miguel angel
        //podemos recuperarlo y poder usar su scheduler de forma sencilla
        CalendarPK pk = new CalendarPK(yearMonth.getMonthValue(), yearMonth.getYear());

        Optional<Calendar> calendar = calendarRepository.findById(pk);
        if (!calendar.isPresent()) {
            logger.info("Trying to generate a schedule for a non existing calendar");
        }

        if (scheduleRepository.findById(pk).isPresent()) {
            logger.info("The schedule is already generated");
        }
        logger.info("Persisting a schedule with status " + ScheduleStatus.BEING_GENERATED);
        Schedule schedule = new Schedule(ScheduleStatus.BEING_GENERATED);
        schedule.setCalendar(calendar.get());
        scheduleRepository.save(schedule);

        // This will be run in a separate thread, so the call is non-blocking
        schedulerHandler.startScheduleGeneration(calendar.get());

        // Recuperamos el schedule para guardar su id en el proceso que sera el mes y año de ese calendario
        int scheduleMonth =  scheduleRepository.findById(pk).get().getMonth();
        int scheduleYear = scheduleRepository.findById(pk).get().getYear();
        // Para posteriormente en la tarea Validar planificacion podamos obtener el schedule
        String idPlanificacionProvisional = scheduleMonth + "-" + scheduleYear;
        logger.info("El id de la planificacion es " + idPlanificacionProvisional);
        Map<String, Object> results = new HashMap<>();
        results.put("IdPlanificacionProvisional", idPlanificacionProvisional);
        workItemManager.completeWorkItem(workItem.getId(), results);
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {

    }
}
