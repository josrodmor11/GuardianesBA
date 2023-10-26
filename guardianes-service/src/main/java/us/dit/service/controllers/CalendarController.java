/**
 * 
 */
package us.dit.service.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO: El controlador para manejar los calendarios
 */
@Controller
@RequestMapping("/guardianes")
public class CalendarController {
	
		@GetMapping("/calendars")
		public String getAll(HttpSession session, Model model) {
			return "calendar";
		}
}
