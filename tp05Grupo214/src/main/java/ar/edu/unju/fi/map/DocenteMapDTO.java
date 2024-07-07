package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.model.Docente;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocenteMapDTO {

	@Mapping(source = "legajo", target = "legajo")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "apellido", target = "apellido")
	@Mapping(source = "email", target = "email")


	DocenteDTO convertirDocenteADocenteDTO(Docente d);

	@Mapping(target = "telefono", ignore = true)
	@Mapping(target = "estado", ignore = true)

	@InheritInverseConfiguration
	Docente convertirDocenteDTOADocente(DocenteDTO ddto);

	List<DocenteDTO> convertirListaDocentesAListaDocentesDTO(List<Docente> listaD);

	List<Docente> convertirListaDocentesDTOAListaDocentes(List<DocenteDTO> listaDDTO);

}
