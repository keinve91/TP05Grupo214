package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.service.AlumnoService;
import jakarta.validation.Valid;

@Controller
public class AlumnoController {
    @Autowired
    Alumno nuevoAlumno;

    @Autowired
    AlumnoService alumnoService;

    @GetMapping("/formularioAlumno")
    public ModelAndView getFormAlumno() {
        ModelAndView modelView = new ModelAndView("formAlumno");
        modelView.addObject("nuevoAlumno", nuevoAlumno);
        modelView.addObject("bandera", false);
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
                modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnosDTO());
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
        modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnosDTO());
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
                modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnosDTO());
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
        modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnosDTO());
        return modelView;
    }

}
