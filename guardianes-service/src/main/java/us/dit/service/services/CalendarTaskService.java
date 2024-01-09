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


@Service
public class CalendarTaskService {

	private static final Logger logger = LogManager.getLogger();

	private final String CalendarTaskName = "EstablecerFestivos";
	@Autowired
	private KieUtilService kieUtils;

	@Autowired
	private TasksService tasksService;
	@Value("${kieserver.containerId}")
	private String containerId;
	@Value("${kieserver.processId}")
	private String processId;

	public void initCalendarTask(HttpSession session, String principal) {
		logger.info("Iniciamos la tarea de calendario");
		List<TaskSummary> taskList = tasksService.findPotential(principal);
		UserTaskServicesClient userClient = kieUtils.getUserTaskServicesClient();

		List<TaskSummary> tasksFilteredByName = taskList.stream()
				.filter(task -> task.getName().equals(CalendarTaskName))
				.collect(Collectors.toList());

		if(!tasksFilteredByName.isEmpty()) {
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
        Calendario calendarioFestivos = new Calendario((long) festivos.size(), festivos);
        //Construimos el mapa con los parámetros de salida
        Map<String, Object> params = new HashMap<>();
        params.put("calendario_Festivos", calendarioFestivos);
        //Finalizamos la tarea
        userClient.completeTask(containerId, task.getId(), principal, params);
    }

	
	
	
}
