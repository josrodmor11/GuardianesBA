package us.dit.service.controllers;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;

//import jakarta.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import us.dit.service.config.ClearPasswordService;
import us.dit.service.services.HolaService;

/**
 * Controlador ejemplo para arrancar el proceso hola
 * TIENE QUE DESAPARECER, NO CUMPLE REST, ES SÓLO PARA COMENZAR A TRABAJAR
 */
@RestController
@RequestMapping("/guardianes/procesohola")
public class HolaController {
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private HolaService hola;
	@Autowired
	private ClearPasswordService clear;

	@GetMapping("/nuevo")
	@ResponseBody
	public String nuevoproceso(HttpSession session) {
		logger.info("ejecutando nuevoproceso");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) auth.getPrincipal();
		logger.info("Datos de usuario " + user);
		logger.info("pwd de usuario " + clear.getPwd(user.getUsername()));

		// Para conseguir el password en claro he delegado en alguna clase que
		// implemente la interfaz ClearPasswordService
		// La implementación que tengo ahora mismo guarda en memoria un mapa de nombre
		// de usuario clave en claro
		// Evidentemente será necesario modificar esto en producción
		Long idInstancia = hola.nuevaInstancia(user.getUsername());
		logger.info("vuelve de la invocación nueva instancia");
		return " Instancia: " + idInstancia.toString();
	}

}
