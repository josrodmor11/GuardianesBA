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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import us.dit.service.services.HolaService;

/**
 * Controlador ejemplo para arrancar el proceso hola
 * TIENE QUE DESAPARECER, ES SÓLO PARA COMENZAR A TRABAJAR
 */
@RestController
@RequestMapping("/guardianes/procesos/hola")
public class HolaController {
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private HolaService hola;
	//Un post a guardianes/procesos/hola crea una instancia del proceso hola en nombre del usuario que esté autenticado en ese momento (en la cabecera authentication modo basic)
	//Para probar este método puede:
	//curl --location --request POST 'http://localhost:8090/guardianes/procesos/hola' --header 'Authorization: Basic dXNlcjp1c2Vy' , el usuario user crea una instancia del proceso hola
	//Usar postman y recordar poner en las cabeceras autenticación básica con un usuario y contraseña que estén en la configuración de seguridad (usuarios en memoria)
	@PostMapping()
	@ResponseBody
	//Con esta etiqueta el valor devuelto se convierte a JSON y se incluye en el cuerpo de la respuesta
	//En los casos en los que se crea un objeto sería conveniente devolver el código http adecuado (nuevo recurso) e incluir la referencia del nuevo recurso
	public Long nuevoproceso(HttpSession session) {
		logger.info("creando nueva instancia del proceso hola");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) auth.getPrincipal();
		logger.info("Datos de usuario " + user);
		// Para conseguir el password en claro he delegado en alguna clase que
		// implemente la interfaz ClearPasswordService
		// La implementación que tengo ahora mismo guarda en memoria un mapa de nombre
		// de usuario clave en claro
		// Evidentemente será necesario modificar esto en producción
		Long idInstancia = hola.nuevaInstancia(user.getUsername());
		logger.info("vuelve de la invocación nueva instancia");
		return idInstancia;
	}

}
