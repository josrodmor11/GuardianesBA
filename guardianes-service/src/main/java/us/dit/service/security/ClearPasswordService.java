package us.dit.service.security;
/**
 * Interfaz de un único método que devuelve la password a partir del nombre de usuario
 */
public interface ClearPasswordService {

	String getPwd(String key);

}