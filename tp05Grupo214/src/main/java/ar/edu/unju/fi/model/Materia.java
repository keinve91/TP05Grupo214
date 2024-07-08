package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.constantes.Modalidad;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	@NotBlank(message="Este campo es obligatorio.")
	@Pattern(regexp = "^cod[0-9]+$", message = "El legajo debe iniciar con cod y número de codigo. Ejemplo: cod1,cod2")
	private String codigo;
	
	@NotBlank(message="Este campo es obligatorio.")
	@Size(min=3, max=50, message="Este campo debe tener entre entre 3 y 50 caracteres.")
	private String nombre;
	
	@NotBlank(message="Este campo es obligatorio.")
	@Size(min=3, max=15, message="Este campo debe tener entre entre 3 y 15 caracteres.")
	private String curso;
	
	@NotNull(message="Este campo es obligatorio.")
    @Pattern(regexp = "^1\\d{2}$", message = "El valor debe ser un número entre 100 y 199 horas")
	private String duracion;
	
	@Enumerated(EnumType.STRING)
	private Modalidad modalidad;
	
	private boolean estado;
	
	@OneToOne
	@JoinColumn(name = "legajo")
	private Docente docente;
	

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
	    name = "materia_alumno",
	    joinColumns = @JoinColumn(name = "codigo"),
	    inverseJoinColumns = @JoinColumn(name = "dni")
	)
	private List<Alumno> alumnos;
	
	 @ManyToOne()
	 @JoinColumn(name = "carrera_id")
    private Carrera carrera;
}
