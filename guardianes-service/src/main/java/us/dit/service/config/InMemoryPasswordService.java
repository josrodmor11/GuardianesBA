package us.dit.service.config;

import java.util.HashMap;

/**
 * Implementación del servicio ClearPasswordService para la identificación en
 * memoria En producción habrá que crear otra implementación que acceda a la
 * base de datos de usuario o a un servicio externo
 */
public class InMemoryPasswordService implements ClearPasswordService {
	private static HashMap<String, String> passwords;

	InMemoryPasswordService() {
		passwords = new HashMap<String, String>();
		passwords.put("user", "user");
		passwords.put("wbadmin", "wbadmin");
		passwords.put("kieserver", "kieserver");
	}

	@Override
	public String getPwd(String key) {
		return passwords.get(key);
	}
}
