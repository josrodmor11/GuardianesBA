/**
 * 
 */
package us.dit.service.services;

import us.dit.service.services.KieUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.server.api.model.instance.TaskInstance;
import org.kie.server.api.model.instance.TaskSummary;
/* https://javadoc.io/doc/org.kie.server/kie-server-api/latest/org/kie/server/api/model/instance/TaskSummary.html */
import org.kie.server.client.UserTaskServicesClient;
import org.kie.server.client.admin.UserTaskAdminServicesClient;
/* https://javadoc.io/doc/org.kie.server/kie-server-client/latest/org/kie/server/client/UserTaskServicesClient.html */
import org.kie.api.task.model.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TasksService {
	@Value("${kieserver.location}")
	private String URL;
	private static final Logger logger = LogManager.getLogger();
	private KieUtilService kie = null;

	/**
	 * Busca todas las tareas de un usuario
	 * 
	 * @param user     El id del "actualOwner" de la tarea (ActorId en las variables
	 *                 de entrada a la tarea)
	 * @param password Password del usuario
	 * @return Una lista de TaskSummaries (con la información más relevante de las
	 *         tareas asignadas al usuario
	 */
	public List<TaskSummary> findAll(String user, String password) {
		logger.info("En findAll de TaskService");
		logger.info("Creando el kieutil con URL " + URL);
		kie = new KieUtil(URL, user, password);
		logger.info("el kieUTIL creado ok");
		List<TaskSummary> taskList = null;

		UserTaskServicesClient client = kie.getUserTaskServicesClient();
		logger.info("Llamo a findTasks de UserTaskServicesClient");
		taskList = client.findTasks(user, 0, 0);
		logger.info("Termino findTasks");
		for (TaskSummary task : taskList) {
			System.out.println("Tarea: " + task);
		}
		return taskList;
	}

	public List<TaskSummary> findByStatus(String user, String password, String state) {
		List<String> status = new ArrayList<String>();
		status.add(state);
		logger.info("Creando el kieutil con URL " + URL);
		kie = new KieUtil(URL, user, password);
		logger.info("el kieUTIL creado ok");
		List<TaskSummary> taskList = null;

		UserTaskServicesClient client = kie.getUserTaskServicesClient();
		logger.info("Llamo a findTasksByVariableAndValue de UserTaskServicesClient");
		taskList = client.findTasksByVariableAndValue(user, "actualOwner", user, status, 0, 0);
		logger.info("Termino findTasksByVariableAndValue");
		for (TaskSummary task : taskList) {
			System.out.println("Tarea: " + task);
		}
		return taskList;
	}
	/**
	 * status.add("Completed"); status.add("Created"); status.add("Error");
	 * status.add("Exited"); status.add("Failed"); status.add("InProgress");
	 * status.add("Obsolete"); status.add("Ready"); status.add("Reserved");
	 * status.add("Suspended"); taskList = uTSC.findTasksByVariableAndValue(user,
	 * "user", user,status , 7, 7); for(TaskSummary task : taskList){
	 * System.out.println("Tarea: "+task); }
	 */
	/**
	 * extraído de
	 * https://access.redhat.com/documentation/en-us/red_hat_process_automation_manager/7.9/html/deploying_and_managing_red_hat_process_automation_manager_services/kie-server-java-api-con_kie-apis
	 * Para usar el QueryService
	 * 
	 * // Client setup KieServicesConfiguration conf =
	 * KieServicesFactory.newRestConfiguration(SERVER_URL, LOGIN, PASSWORD);
	 * KieServicesClient client = KieServicesFactory.newKieServicesClient(conf);
	 * 
	 * // Get the QueryServicesClient QueryServicesClient queryClient =
	 * client.getServicesClient(QueryServicesClient.class);
	 * 
	 * // Build the query QueryDefinition queryDefinition =
	 * QueryDefinition.builder().name(QUERY_NAME) .expression("select * from Task
	 * t") .source("java:jboss/datasources/ExampleDS") .target("TASK").build();
	 * 
	 * // Specify that two queries cannot have the same name
	 * queryClient.unregisterQuery(QUERY_NAME);
	 * 
	 * // Register the query queryClient.registerQuery(queryDefinition);
	 * 
	 * // Execute the query with parameters: query name, mapping type (to map the
	 * fields to an object), page number, page size, and return type
	 * List<TaskInstance> query = queryClient.query(QUERY_NAME,
	 * QueryServicesClient.QUERY_MAP_TASK, 0, 100, TaskInstance.class);
	 * 
	 * // Read the result for (TaskInstance taskInstance : query) {
	 * System.out.println(taskInstance); }
	 * 
	 * 
	 */

	public TaskInstance findById(String user, String pwd, Long taskId) {
		logger.info("En findAll de TaskService");
		logger.info("Creando el kieutil con URL " + URL);
		kie = new KieUtil(URL, user, pwd);
		logger.info("el kieUTIL creado ok");
		TaskInstance task = null;

		UserTaskServicesClient client = kie.getUserTaskServicesClient();
		logger.info("Llamo a findTaskById de UserTaskServicesClient");
		task = client.findTaskById(taskId);
		logger.info("Termino findTaskById");
		
		return task;
	}

}
