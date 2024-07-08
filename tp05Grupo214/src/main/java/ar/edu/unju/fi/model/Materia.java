package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Component
@Entity
public class Materia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String codigo;
	
	@NotBlank(message="Este campo es obligatorio.")
	@Size(min=3, max=50, message="Este campo debe tener entre entre 3 y 50 caracteres.")
	private String nombre;
	
	@NotBlank(message="Este campo es obligatorio.")
	@Size(min=3, max=15, message="Este campo debe tener entre entre 3 y 15 caracteres.")
	private String curso;
	
	@NotNull(message="Este campo es obligatorio.")
	@Min(value=100, message="Este campo requiere un número mínimo de 100")
	@Max(value=300, message="Este campo requiere un número máximo de 500")
	private int duracion;
	
	@NotNull(message="Este campo es obligatorio.")
	private boolean modalidad;
	
	private boolean estado;
	
	@OneToOne
	@JoinColumn(name="legajo")
	private Docente docente;
	
	/*
	 * @ManyToMany(fetch = FetchType.EAGER) private List<Alumno> alumnos;
	 */	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carrera_id")
	private Carrera carrera;
}
