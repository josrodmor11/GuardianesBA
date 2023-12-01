package us.dit.service.services;

import java.time.LocalDate;
import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.dit.model.Calendario;

import org.kie.server.api.model.instance.WorkItemInstance;
import org.kie.server.client.ProcessServicesClient;


@Service
public class HumanTaskService {

	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private KieUtilService kieUtils;
	
	private String containerId="guardianes-kjar-1_0-SNAPSHOT";
	private String processId="guardianes-kjar.GenerarPlanificacion";

	public Long nuevaInstancia(String principal) {
		Map<String, Object> variables = new HashMap<String,Object>();
		logger.info("Creamos la instancia del proceso" + processId);
		variables.put("principal", principal);
		
		ProcessServicesClient client  = kieUtils.getProcessServicesClient();
		Long idInstanceProcess = client.startProcess(containerId, processId, variables);
		logger.info("El id de la instancia del proceso es " + idInstanceProcess);
		
		return idInstanceProcess;
		
	}
	
	public Calendario iniciarTarea(HttpSession session) {
		logger.info("Iniciamos la tarea " + processId);
		WorkItemInstance instanciaTarea = buscarSiguienteTarea((Long) session.getAttribute("processId"));
		session.setAttribute("instanciaTarea", instanciaTarea);
		Calendario calendarioFestivos = ClienteBBDD.obtenerCalendario();
		logger.info("Recuperado calendario con los festivos, que son: " + calendarioFestivos.obtenerFestivos());
		session.setAttribute("calendarioFestivos", calendarioFestivos);
		//Aquí crear directamente el calendario y ya
		return calendarioFestivos;
		
	}
	
	private WorkItemInstance buscarSiguienteTarea(Long processId) {
		logger.info("Entro en findNextTask con processId: "+processId);				
		logger.info("Creo cliente de procesos");
		ProcessServicesClient processClient = kieUtils.getProcessServicesClient();
		logger.info("Llamo a findNodeInstances del cliente de procesos");
		
		WorkItemInstance wi = processClient.getWorkItemByProcessInstance(containerId, processId).get(0);
		logger.info("WI: "+wi.toString());		
		return wi;
	}
	
	
	
}
