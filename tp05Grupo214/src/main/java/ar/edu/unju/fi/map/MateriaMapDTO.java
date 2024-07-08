package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MateriaMapDTO {
	@Mapping(source="codigo",target="codigoMa")
	@Mapping(source="nombre",target="nombreMa")
	@Mapping(source="curso",target="cursoMa")
	@Mapping(source="duracion",target="duracionMa")
	@Mapping(source="docente",target="docenteMa")
	@Mapping(source="carrera",target="carreraMa")
	@Mapping(source = "modalidad", target = "modalidad")

	MateriaDTO convertirMateriaAMateriaDTO(Materia m);
	
	@Mapping(target = "estado", ignore = true)
	@InheritInverseConfiguration
	Materia convertirMateriaDTOAMateria(MateriaDTO m);
	
	List<MateriaDTO> convertirListaMateriasAListaMateriasDTO(List<Materia> materias);
	
	List<Materia> convertirListaMateriasDTOAListaMaterias(List<MateriaDTO> materias);
	
	
}
