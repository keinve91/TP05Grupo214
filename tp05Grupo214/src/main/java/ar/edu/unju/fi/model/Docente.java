package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
public class Docente {
	@Id
	
	@NotBlank(message = "Este campo es Obligatorio.")
    @Pattern(regexp = "^LEG_\\d{2}$", message = "El codigo debe ser 'LEG_xx'.")
	private String legajo;

	@NotBlank(message = "Este campo es Obligatorio.")
	@Size(min = 4, max = 35, message = "Este campo debe tener entre 4 y 35 caracteres.")
	@Pattern(regexp = "[a-z A-Z]*", message = "Este campo solo debe contener Letras.")
	private String nombre;

	@NotBlank(message = "Este campo es Obligatorio.")
	@Size(min = 3, max = 10, message = "Este campo debe tener entre 3 y 10 caracteres.")
	@Pattern(regexp = "[a-z A-Z]*", message = "Este campo solo debe contener Letras.")
	private String apellido;

	@NotBlank(message = "Este campo es Obligatorio.")
    @Pattern(regexp = "^[\\w\\.-]+@gmail\\.com$", message = "El correo electrónico debe terminar en @gmail.com")
	private String email;
	
	
	@NotBlank(message = "Este campo es Obligatorio.")
	@Size(min = 3, max = 15, message = "Este campo debe tener entre 3 y 15 caracteres.")
    @Pattern(regexp = "54388\\d{7}", message = "El número de teléfono debe seguir el patrón 54388xxxxxxx")
	private String telefono;
	
	private Boolean estado;

}
