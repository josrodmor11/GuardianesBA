package us.dit.service.services;

import us.dit.service.services.KieUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.server.client.ProcessServicesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HolaService {
	@Value("${kieserver.location}")
	private String URL;
	private static final Logger logger = LogManager.getLogger();
	private KieUtilService kie = null;

	public Long nuevaInstancia(String user, String password) {
		// String contentHtml = null;

		logger.info("Creando el kieutil con URL " + URL);
		kie = new KieUtil(URL, user, password);

		logger.info("el kieUTIL creado ok");
		Long idInstanceProcess = newInstanceProcess();

		return idInstanceProcess;
	}

	// Crea una nueva instancia del proceso
	private Long newInstanceProcess() {
		logger.info("En newInstanceProcess");
		Long idInstanceProcess = null;

		ProcessServicesClient processServicesClient = kie.getProcessServicesClient();

		logger.info("Trato de crear proceso guardianes-kjar.prueba");
		idInstanceProcess = processServicesClient.startProcess("guardianes-kjar-1.0-SNAPSHOT",
				"guardianes-kjar.prueba");
		logger.info("conseguido??? " + idInstanceProcess.toString());

		return idInstanceProcess;
	}

}
