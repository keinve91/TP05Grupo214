package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.constantes.Modalidad;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class MateriaDTO {
	private String codigo;
	private String nombre;
	private String curso;
	private String duracion;
	private Modalidad modalidad;
	private Docente docente;
	private Carrera carrera;
	private boolean estado;

}
