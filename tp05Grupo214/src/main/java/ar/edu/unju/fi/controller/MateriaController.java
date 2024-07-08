package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;

@Controller
public class MateriaController {
	
	@Autowired
	private Materia nuevaMateria;
	
	@Autowired
	private MateriaService materiaService;
	
	@Autowired
	private DocenteService docenteService;
	
	@Autowired
	private CarreraService carreraService;
	
	@GetMapping("/formularioMateria")
	public ModelAndView getFormMateria() {		
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentesDTO());
		modelView.addObject("listadoCarreras", carreraService.mostrarCarrerasDTO());
		modelView.addObject("nuevaMateria", nuevaMateria);
		modelView.addObject("flag", false);
		return modelView;
	}
	
	@PostMapping("/guardarMateria")
	public ModelAndView saveMateria(@Valid @ModelAttribute("nuevaMateria") Materia m, BindingResult resultado) {
		ModelAndView modelView = new ModelAndView("listaDeMaterias");	
		try {
			if (resultado.hasErrors()) {
				modelView.addObject("listadoDocentes", docenteService.mostrarDocentesDTO());
				modelView.addObject("listadoCarreras", carreraService.mostrarCarrerasDTO());
				modelView.setViewName("formMateria");
			} else {
				m.setCarrera(carreraService.buscarCarrera(m.getCarrera().getCodigo()));
				m.setDocente(docenteService.buscarDocente(m.getDocente().getLegajo()));
				materiaService.guardarMateria(m);
				System.out.println("Materia guardada correctamente");
				modelView.addObject("listadoMaterias", materiaService.mostrarMateriasDTO());
			}					
		} catch (Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargaMateriaErrorMessage", "Error al cargar en la BD " + e.getMessage());
			System.out.println(e.getMessage());
		}		
		return modelView;
	}
	
	@GetMapping("/mostrarMaterias")
	public ModelAndView listarLasMaterias() {
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias", materiaService.mostrarMateriasDTO());		
		return modelView;
	}
	
	@GetMapping("/modificarMateria/{codigo}")
	public ModelAndView editarMateria(@PathVariable(name = "codigo") String codigo) {
		Materia m = materiaService.buscarMateria(codigo);
		ModelAndView modelView = new ModelAndView("formMateria");	
		modelView.addObject("nuevaMateria", m);
		modelView.addObject("flag", true);
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentesDTO());
		modelView.addObject("listadoCarreras", carreraService.mostrarCarrerasDTO());
		return modelView;
	}
	
	@PostMapping("/modificarMateria")
	public ModelAndView modificarMateriaListado(@Valid @ModelAttribute("nuevaMateria") Materia m, BindingResult resultado) {
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		try {
			if (resultado.hasErrors()) {
				modelView.addObject("listadoCarreras", carreraService.mostrarCarrerasDTO());
				modelView.addObject("listadoDocentes", docenteService.mostrarDocentesDTO());
				modelView.setViewName("formMateria");
			} else {		
				m.setCarrera(carreraService.buscarCarrera(m.getCarrera().getCodigo()));
				m.setDocente(docenteService.buscarDocente(m.getDocente().getLegajo()));
				materiaService.modificarMateria(m);
				System.out.println("Materia modificada correctamente");
				modelView.addObject("listadoMaterias", materiaService.mostrarMateriasDTO());
			}					
		} catch (Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargaMateriaErrorMessage", "Error al cargar en la BD " + e.getMessage());
			System.out.println(e.getMessage());
		}
		return modelView;
	}
	
	@GetMapping("/eliminarMateria/{codigo}")
	public ModelAndView borrarMateriaListado(@PathVariable(name = "codigo") String codigo) {
		materiaService.borrarMateria(codigo);
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias", materiaService.mostrarMateriasDTO());	
		return modelView;
	}
}
