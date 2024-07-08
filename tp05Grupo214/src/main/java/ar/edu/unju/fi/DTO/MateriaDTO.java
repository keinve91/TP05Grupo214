package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

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
	private String codigoMa;
	private String nombreMa;
	private String cursoMa;
	private int duracionMa;
	private Docente docenteMa;
	private Carrera carreraMa;
	private boolean modalidad;

}
