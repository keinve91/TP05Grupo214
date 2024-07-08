package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    AlumnoMapDTO alumnoMapDTO;

    @Override
    public void guardarAlumno(Alumno alumno) {
        alumno.setEstado(true);
        alumnoRepository.save(alumno);
    }

    @Override
    public List<AlumnoDTO> mostrarAlumnosDTO() {
        List<Alumno> alumnosActivos = alumnoRepository.findAlumnoByEstado(true);
        return alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnosActivos);
    }

    @Override
    public void borrarAlumno(String lu) {
        Alumno alumno = buscarAlumno(lu);
        alumno.setEstado(false);
        alumnoRepository.save(alumno);
    }

    @Override
    public void modificarAlumno(Alumno alumno) {
        alumno.setEstado(true);
        alumnoRepository.save(alumno);
    }

    @Override
    public Alumno buscarAlumno(String lu) {
        return alumnoRepository.findById(lu).orElse(null);
    }
}
