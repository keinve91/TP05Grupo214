package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.CarreraService;
import jakarta.validation.Valid;

@Controller
public class CarreraController {

    @Autowired
    private Carrera nuevaCarrera;

    @Autowired
    private CarreraService carreraService;

    @GetMapping("/formularioCarrera")
    public ModelAndView getFormCarrera() {
        ModelAndView modelView = new ModelAndView("formCarrera");
        modelView.addObject("nuevaCarrera", nuevaCarrera);
        modelView.addObject("bandera", false);
        return modelView;
    }

    @PostMapping("/guardarCarrera")
    public ModelAndView saveCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraGuardar, BindingResult resultado) {
        if (resultado.hasErrors()) {
            ModelAndView modelView = new ModelAndView("formCarrera");
            return modelView;
        }
        carreraService.guardarCarrera(carreraGuardar);
        return new ModelAndView("redirect:/mostrarCarreras");
    }

    @GetMapping("/eliminarCarrera/{codigo}")
    public ModelAndView eliminarCarreraDelListado(@PathVariable(name = "codigo") String codigo) {
        carreraService.borrarCarrera(codigo);
        return new ModelAndView("redirect:/mostrarCarreras");
    }

    @GetMapping("/modificarCarrera/{codigo}")
    public ModelAndView modificarCarreraDelListado(@PathVariable(name = "codigo") String codigo) {
        Carrera c = carreraService.buscarCarrera(codigo);
        ModelAndView modelView = new ModelAndView("formCarrera");
        modelView.addObject("nuevaCarrera", c);
        modelView.addObject("bandera", true);
        return modelView;
    }

    @PostMapping("/modificarCarrera")
    public ModelAndView updateCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraModificada, BindingResult resultado) {
        if (resultado.hasErrors()) {
            ModelAndView modelView = new ModelAndView("formCarrera");
            modelView.addObject("bandera", true);
            return modelView;
        }
        carreraService.modificarCarrera(carreraModificada);
        return new ModelAndView("redirect:/mostrarCarreras");
    }

    @GetMapping("/mostrarCarreras")
    public ModelAndView verCarrera() {
        ModelAndView modelView = new ModelAndView("listaDeCarreras");
        modelView.addObject("listadoCarreras", carreraService.mostrarCarrerasDTO());
        return modelView;
    }
}