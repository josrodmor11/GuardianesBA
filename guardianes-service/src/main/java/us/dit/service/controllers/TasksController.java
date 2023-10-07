package us.dit.service.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.server.api.model.instance.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import us.dit.service.security.ClearPasswordService;
import us.dit.service.services.TasksService;

/**
 * Controlador ejemplo para arrancar el proceso hola
 */
@Controller
@RequestMapping("/myTasks")
public class TasksController {
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private TasksService tasksService;
	@Autowired
	private ClearPasswordService clear;

	@GetMapping
	@ResponseBody
	public List<TaskSummary> nuevoproceso(HttpSession session) {
		logger.info("buscando todas las tareas del usuario");
		List<TaskSummary> tasksList = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) auth.getPrincipal();
		logger.info("Datos de usuario " + user);
		logger.info("pwd de usuario " + clear.getPwd(user.getUsername()));

		// Para conseguir el password en claro he delegado en alguna clase que
		// implemente la interfaz ClearPasswordService
		// La implementación que tengo ahora mismo guarda en memoria un mapa de nombre
		// de usuario clave en claro
		// Evidentemente será necesario modificar esto en producción
		tasksList = tasksService.findAll(user.getUsername(), clear.getPwd(user.getUsername()));
		logger.info("vuelve de consultar tareas");
		return tasksList;
	}

}
