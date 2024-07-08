package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Service
public interface AlumnoService {

    public void guardarAlumno(Alumno alumno);

    public List<AlumnoDTO> mostrarAlumnosDTO();

    public void borrarAlumno(String lu);

    public void modificarAlumno(Alumno alumno);

    public Alumno buscarAlumno(String lu);
}
