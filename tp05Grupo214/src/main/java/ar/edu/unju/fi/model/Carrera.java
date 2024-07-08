package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
public class Carrera {
	
	@Id
	@NotBlank(message="Debe ingresar un codigo")
    @Pattern(regexp = "^cod_\\d{2}$", message = "El codigo debe ser 'cod_XX'.")
	private String codigo;

	@NotBlank(message = "Debe ingresar el nombre de la carrera")
	@Size(min = 5, max = 50, message = "El nombre debe contener como mínimo 5 caracteres y 50 como máximo")
	@Pattern(regexp="[a-zA-Z ]+", message="Solo se deben ingresar letras y espacios")
	private String nombre;
	
	@Min(value = 1, message = "La cantidad de años debe ser al menos 1")
    @Max(value = 10, message = "La cantidad de años no puede ser mayor a 10")
	private int cantidadAnios;
	
	private Boolean estado;
    
}
