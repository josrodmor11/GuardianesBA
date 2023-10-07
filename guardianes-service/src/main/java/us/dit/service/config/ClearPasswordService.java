package us.dit.service.config;
/**
 * Interfaz de un único método que devuelve la password a partir del nombre de usuario
 */
public interface ClearPasswordService {

	String getPwd(String key);

}