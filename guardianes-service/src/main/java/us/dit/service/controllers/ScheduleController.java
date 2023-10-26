/**
 * 
 */
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import us.dit.service.services.TasksService;

/**
 * TODO: El controlador para manejar las planificaciones (Agendas)
 */
@Controller
@RequestMapping("/guardianes")
public class ScheduleController {
	
	@GetMapping("/schedules")
	public String getAll(HttpSession session, Model model) {
		return "schedule";
	}

	@GetMapping("/schedules/{scheduleId}")
	
	public String getScheduleById(@PathVariable Long taskId,HttpSession session) {
	return "schedule";
	}
		
}
