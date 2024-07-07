package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarreraServiceImp implements CarreraService {

	@Autowired
	CarreraRepository carreraRepository;

	@Autowired
	CarreraMapDTO carreraMapDTO;

	@Override
	public void guardarCarrera(Carrera carrera) {
		log.info("SERVICE: CarreraServiceImp -> guardarCarrera");
		log.info("METHOD: guardarCarrera()");
		log.info("INFO: Guardando Carrera con código {}", carrera.getCodigo());

		carrera.setEstado(true);
		carreraRepository.save(carrera);
		log.info("INFO: Carrera guardada exitosamente");
	}

	@Override
	public List<CarreraDTO> mostrarCarrerasDTO() {
		log.info("SERVICE: CarreraServiceImp -> mostrarCarrerasDTO");
		log.info("METHOD: mostrarCarrerasDTO");
		return carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO(carreraRepository.findCarreraByEstado(true));
	}

	@Override
	public void borrarCarrera(String codigo) {
		log.info("SERVICE: CarreraServiceImp -> borrarCarrera");
		log.info("METHOD: borrarCarrera()");
		log.info("INFO: Borrando carrera con código {}", codigo);
		List<Carrera> todasLasCarreras = carreraRepository.findAll();
		for (int i = 0; i < todasLasCarreras.size(); i++) {
			Carrera c = todasLasCarreras.get(i);
			if (c.getCodigo().equals(codigo)) {
				c.setEstado(false);
				carreraRepository.save(c);
				log.info("INFO: Carrera con código {}", codigo, "borrada exitosamente");
				break;
			}
		}
	}

	@Override
	public void modificarCarrera(Carrera carreraMod) {
		log.info("SERVICE: CarreraServiceImp -> modificarCarrera()");
		log.info("INFO: Modificando carrera con código {}", carreraMod.getCodigo());
		List<Carrera> todasLasCarreras = carreraRepository.findAll();
		for (int i = 0; i < todasLasCarreras.size(); i++) {
			Carrera c = todasLasCarreras.get(i);
			if (c.getCodigo().equals(carreraMod.getCodigo())) {
				carreraMod.setEstado(true);
				carreraRepository.save(carreraMod);
				log.info("INFO: Carrera con código {} modificada exitosamente", carreraMod.getCodigo());
				break;
			}
		}
	}

	@Override
	public Carrera buscarCarrera(String codigo) {
		log.info("SERVICE: CarreraServiceImp -> buscarCarrera");
		log.info("METHOD: buscarCarrera()");
		log.info("INFO: Buscando carrera con código {}", codigo);
		Carrera carrera = carreraRepository.findById(codigo).orElse(null);
		if (carrera == null) {
			log.info("ERROR: No se encontró la carrera con código {}", codigo);
		} else {
			log.info("INFO: Carrera con código {} encontrada exitosamente", codigo);
		}
		return carrera;
	}
}
