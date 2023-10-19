package us.dit.service.services;


import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.server.client.ProcessServicesClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * ESTE SERVICIO DESAPARECERÁ, ES SÓLO PARA PRUEBAS
 */
@Service
public class HolaService {

	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private KieUtilService kie;
	public Long nuevaInstancia(String principal) {
		Map<String,Object> variables= new HashMap<String,Object>();
        
	    variables.put("principal", principal);
		ProcessServicesClient client = kie.getProcessServicesClient();
		Long idInstanceProcess = client.startProcess("guardianes-kjar-1.0-SNAPSHOT", "guardianes-kjar.prueba",variables);
		logger.info("conseguido??? " + idInstanceProcess.toString());
		return idInstanceProcess;
	}

}
