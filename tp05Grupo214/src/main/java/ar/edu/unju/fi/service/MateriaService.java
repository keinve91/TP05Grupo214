package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

@Service
public interface MateriaService {
	public void guardarMateria(Materia m);
	public List<MateriaDTO> mostrarMateriasDTO();
	public int buscarPosicionMateria(String codigo);
	public Materia buscarMateria(String codigo);
	public void borrarMateria(String codigo);
	public void modificarMateria(Materia m);
}