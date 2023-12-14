package us.dit.model;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Calendario {
	
	private Set<LocalDate> festivos;

	public Calendario() {
		/*
		 * Al momento de intanciarse este objeto se instancia el HashSet así no hay problemas al agregar festivos a la lista más adelante.
		 */
		this.festivos = new HashSet<>();
	}
	
	public void agregarFestivo(int year, int month, int day) {
		LocalDate festivo = LocalDate.of(year, month, day);
		festivos.add(festivo);
	}
	
	public boolean esFestivo(LocalDate fecha) {
		return festivos.contains(fecha);
	}
	
	public void eliminarFestivo(LocalDate fecha) {
		festivos.remove(fecha);
	}
	
	public Set<LocalDate> obtenerFestivos() {
		return festivos;
	}
	

}
