package ar.edu.unju.fi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.AlumnoService;
import jakarta.validation.Valid;
import ar.edu.unju.fi.service.*;

@Controller
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    MateriaService materiaService;
    
    @Autowired
	Alumno nuevoAlumno;

	@Autowired
	Materia nuevaMateria;
	
	@Autowired
	CarreraService carreraService;
    
    @GetMapping("/formularioAlumno")
    public ModelAndView getFormAlumno() {
        ModelAndView modelView = new ModelAndView("formAlumno");
        modelView.addObject("nuevoAlumno", nuevoAlumno);
        modelView.addObject("bandera", false);
        modelView.addObject("listadoCarreras", carreraService.mostrarCarrerasDTO());
        return modelView;
    }

    @PostMapping("/guardarAlumno")
    public ModelAndView saveAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumnoGuardar,
            BindingResult resultado) {
        ModelAndView modelView = new ModelAndView("listaDeAlumnos");

        try {
            if (resultado.hasErrors()) {
                modelView.setViewName("formAlumno");
            } else {
                alumnoService.guardarAlumno(alumnoGuardar);
                modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
            }
        } catch (Exception e) {
            boolean errors = true;
            modelView.addObject("errors", errors);
            modelView.addObject("cargaAlumnoErrorMessage", " Error al Cargar en la BD " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelView;
    }

    @GetMapping("/eliminarAlumno/{lu}")
    public ModelAndView eliminarAlumnoDelListado(@PathVariable(name = "lu") String lu) {
        alumnoService.borrarAlumno(lu);
        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
        modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
        return modelView;
    }

    @GetMapping("/modificarAlumno/{lu}")
    public ModelAndView modificarAlumnoDelListado(@PathVariable(name = "lu") String lu) {
        Alumno a = alumnoService.buscarAlumno(lu);
        ModelAndView modelView = new ModelAndView("formAlumno");
        modelView.addObject("nuevoAlumno", a);
        modelView.addObject("bandera", true);
        return modelView;

    }

    @PostMapping("/modificarAlumno")
    public ModelAndView updateAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumnoModificado,
            BindingResult resultado) {

        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
        try {
            if (resultado.hasErrors()) {
                modelView.setViewName("formAlumno");
            } else {
                alumnoService.modificarAlumno(alumnoModificado);
                modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
            }
        } catch (Exception e) {
            boolean errors = true;
            modelView.addObject("errors", errors);
            modelView.addObject("cargaAlumnoErrorMessage", " Error al Cargar en la BD " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return modelView;

    }

    @GetMapping("/mostrarAlumnos")
    public ModelAndView verAlumno() {
        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
        modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
        return modelView;
    }
  //INSCRIPCIONES Y MAS
  	@GetMapping("/formularioInscripcion") // inscribe Alumno en Materias
  	public ModelAndView getFormAlumnoInscripcion() {
  		ModelAndView modelView = new ModelAndView("formAlumnoInsc");		
  		modelView.addObject("nuevoAlumno", nuevoAlumno);
  		modelView.addObject("nuevaMateria", nuevaMateria);
  		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
  		modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
  		return modelView;
  	}
  	@PostMapping("/inscribirAlumno")
	public ModelAndView formularioInscripcion(@ModelAttribute("nuevoAlumno") Alumno alumno, @ModelAttribute("nuevaMateria") Materia materia) {
		ModelAndView modelView = new ModelAndView("ListaDeAlumnos");
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		try {
			if (alumnoService.buscarAlumno(alumno.getLu())!=null){
				alumnoService.inscribirAlumno(alumnoService.buscarAlumno(alumno.getLu()), materiaService.buscarMateria(materia.getCodigo()));			
			}
		}
		catch( Exception e){
			boolean errors = true;
			modelView.addObject("errors", errors);
			modelView.addObject("cargaAlumnoErrorMessage", " Error al cargar en la BD " + e.getMessage());
			System.out.println(e.getMessage());
		}
		return modelView;	
	}
  	@GetMapping("/alumnosPorCarrera/{codigo}")
    public ModelAndView mostrarAlumnosPorCarrera(@PathVariable String codigo) {
        ModelAndView modelView = new ModelAndView("listaAlumnosPorCarrera");
        Carrera carrera = carreraService.buscarCarrera(codigo);
        List<AlumnoDTO> alumnos = alumnoService.obtenerAlumnosPorCarrera(carrera);
        modelView.addObject("carrera", carrera);
        modelView.addObject("listadoAlumnos", alumnos);
        return modelView;
    }
  }

