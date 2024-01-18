package us.dit.service.services;

import java.time.LocalDate;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.client.UserTaskServicesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import us.dit.model.Calendario;
import us.dit.service.dao.JpaCalendarioDao;


@Service
public class CalendarTaskService {

	private static final Logger logger = LogManager.getLogger();

	private final String CalendarTaskName = "EstablecerFestivos";
	@Autowired
	private KieUtilService kieUtils;

	@Autowired
	private TasksService tasksService;

	private JpaCalendarioDao jpaCalendarioDao;
	@Value("${kieserver.containerId}")
	private String containerId;
	@Value("${kieserver.processId}")
	private String processId;

	public void initCalendarTask(HttpSession session, String principal) {
		logger.info("Iniciamos la tarea de calendario");
		List<TaskSummary> taskList = tasksService.findPotential(principal);
		logger.info("Las tareas son " + taskList);
		UserTaskServicesClient userClient = kieUtils.getUserTaskServicesClient();

		List<TaskSummary> tasksFilteredByName = taskList.stream()
				.filter(task -> task.getName().equals(CalendarTaskName))
				.collect(Collectors.toList());

		logger.info("Las tareas filtradas son " + tasksFilteredByName);
		if(!tasksFilteredByName.isEmpty()) {
			logger.info("Hay tareas de calendario disponibles");
			long calendarTaskId = tasksFilteredByName.get(0).getId();
			userClient.claimTask(containerId, calendarTaskId, principal);
			session.setAttribute("tarea", calendarTaskId);
		}

	}

	public void completeCalendarTask(String principal, Set<LocalDate> festivos, TaskSummary task) {
        UserTaskServicesClient userClient = kieUtils.getUserTaskServicesClient();
        //Iniciamos la tarea
        userClient.startTask(containerId, task.getId(), principal);
        //Construimos el calendario
        Calendario calendarioFestivos = new Calendario(festivos);
        //Construimos el mapa con los parámetros de salida
        Map<String, Object> params = new HashMap<>();
        params.put("Id_Calendario_Festivos", calendarioFestivos);
		//Persistimos el calendario
		jpaCalendarioDao.save(calendarioFestivos);
        //Finalizamos la tarea
        userClient.completeTask(containerId, task.getId(), principal, params);
    }

	
	
	
}
