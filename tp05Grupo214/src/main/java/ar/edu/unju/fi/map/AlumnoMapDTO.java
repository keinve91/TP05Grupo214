package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlumnoMapDTO {

    @Mapping(source = "lu", target = "lu")
    @Mapping(source = "dni", target = "dni")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "apellido", target = "apellido")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "fnacimiento", target = "fnacimiento")
    @Mapping(source = "domicilio", target = "domicilio")
    AlumnoDTO convertirAlumnoAAlumnoDTO(Alumno a);

    @Mapping(target = "estado", ignore = true)
    @InheritInverseConfiguration
    Alumno convertirAlumnoDTOAAlumno(AlumnoDTO adto);

    List<AlumnoDTO> convertirListaAlumnosAListaAlumnosDTO(List<Alumno> listaA);

    List<Alumno> convertirListaAlumnosDTOAListaAlumnos(List<AlumnoDTO> listaADTO);
}
