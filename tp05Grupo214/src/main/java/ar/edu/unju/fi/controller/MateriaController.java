package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.MateriaService;
import ar.edu.unju.fi.service.imp.DocenteServiceImp;
import jakarta.validation.Valid;
import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.model.Materia;

@Controller
public class MateriaController {
	@Autowired
	Materia nuevaMateriaDTO;

	@Autowired
	MateriaService materiaService;
	@Autowired
	DocenteService docenteService;
	@Autowired
	CarreraService carreraService;

	@GetMapping("/formularioMateria")
	public ModelAndView getFormMateria() {
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria", nuevaMateriaDTO);
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentesDTO());
		modelView.addObject("listadoCarreras", carreraService.mostrarCarrerasDTO());
		modelView.addObject("flag", false);
		return modelView;
	}

	@PostMapping("/guardarMateria")
	public ModelAndView saveMateria(@Valid @ModelAttribute("nuevaMateria") Materia materiaParaGuardar,BindingResult result) {	
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentesDTO());
		modelView.addObject("listadoCarreras", carreraService.mostrarCarrerasDTO());
		try {
			if (result.hasErrors()) {
				modelView.setViewName("formMateria");
				modelView.addObject("flag", false);
			} else {
				materiaParaGuardar.setDocente(docenteService.buscarDocente(materiaParaGuardar.getDocente().getLegajo()));
				materiaParaGuardar.setCarrera(carreraService.buscarCarrera(materiaParaGuardar.getCarrera().getCodigo()));

				materiaService.guardarMateria(materiaParaGuardar);	

			}
		} catch (Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargaMateriaErrorMessage", "Error al cargar en la BD" + e.getMessage());
			System.out.println(e.getMessage());
		}
		modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());	
		return modelView;
	}

	@GetMapping("/borrarMateria/{codigo}")
	public ModelAndView deleteMateriaDelListado(@PathVariable(name = "codigo") String codigo) {
		// borrar
		materiaService.borrarMateria(codigo);
		// mostrar el listado
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
		return modelView;
	}

	@GetMapping("/modificarMateria/{codigo}")
	public ModelAndView getFormModificarMateria(@PathVariable(name = "codigo") String codigo) {
		// buscar
		Materia materia = materiaService.buscarMateria(codigo);
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria", materia);
		modelView.addObject("flag", false);
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentesDTO());
		modelView.addObject("listadoCarreras", carreraService.mostrarCarrerasDTO());
		return modelView;
	}

	@PostMapping("/modificarMateria")
	public ModelAndView updateMateria(@Valid @ModelAttribute("nuevaMateria") Materia materiaModificada, BindingResult result) {
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		try {
			if (result.hasErrors()) {
				modelView.addObject("nuevaMateria", materiaModificada);
				modelView.setViewName("formMateria");
				modelView.addObject("flag", true);
				modelView.addObject("listadoDocentes", docenteService.mostrarDocentesDTO());
				modelView.addObject("listadoCarreras", carreraService.mostrarCarrerasDTO());
			} else {
				materiaModificada.setCarrera(carreraService.buscarCarrera(materiaModificada.getCarrera().getCodigo()));
				materiaModificada.setDocente(docenteService.buscarDocente(materiaModificada.getDocente().getLegajo()));
				materiaService.modificarMateria(materiaModificada);
			}
		} catch (Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargaMateriaErrorMessage", "Error al cargar en la BD" + e.getMessage());
			System.out.println(e.getMessage());

		}
		modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
		return modelView;
	}

	@GetMapping("/mostrarMaterias")
	public ModelAndView showMaterias() {
		// mostrar el listado
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
		return modelView;

	}

}