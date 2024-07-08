package ar.edu.unju.fi.DTO;

import java.time.LocalDate;


import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ar.edu.unju.fi.model.Carrera;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AlumnoDTO {
    private String lu;
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private LocalDate fnacimiento;
    private Carrera carrera;
    private String domicilio;
}
