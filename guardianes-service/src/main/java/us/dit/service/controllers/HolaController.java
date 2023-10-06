package us.dit.service.controllers;

import javax.servlet.http.HttpSession;

//import jakarta.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import us.dit.service.services.HolaService;

/**
 * 
 */
@Controller
@RequestMapping("/procesohola")
public class HolaController {
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private HolaService hola;

	@GetMapping("/nuevo")
	@ResponseBody
	public String nuevoproceso(HttpSession session) {
		logger.info("ejecutando nuevoproceso");
		// User user = (User) session.getAttribute("user");
		logger.info("Atributos de sesion " + session.getAttributeNames());

		Long idInstancia = hola.nuevaInstancia("wbadmin", "wbadmin");
		logger.info("vuelve de la invocación nueva instancia");
		return " Instancia: " + idInstancia.toString();
	}

}
