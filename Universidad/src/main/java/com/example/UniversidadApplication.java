package com.example;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.entities.Correo;
import com.example.entities.Estudiante;
import com.example.entities.Facultad;
import com.example.entities.Genero;
import com.example.entities.Telefono;
import com.example.services.EstudianteService;
import com.example.services.FacultadService;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class UniversidadApplication implements CommandLineRunner {

	private final FacultadService facultadService;
	private final EstudianteService estudianteService;


	public static void main(String[] args) {
		SpringApplication.run(UniversidadApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Facultad fac1 = Facultad.builder()
		.nombre("MATEMATICAS")
		.build();
		Facultad fac2 = Facultad.builder()
		.nombre("INFORMATICA")
		.build();

		facultadService.persistirUpdateFacultad(fac1);
		facultadService.persistirUpdateFacultad(fac2);

		Estudiante est11;
		Estudiante est21;

		est11 = Estudiante.builder()
		.nombre("Arancha")
		.primerApellido("Rodriguez")
		.segundoApellido("Hernandez")
		.fechaDeMatriculacion(LocalDate.of(2015,Month.SEPTEMBER, 1))
		.facultad(facultadService.getFacultad(1))
		.genero(Genero.MUJER)
		.build();

		est21 = Estudiante.builder()
		.nombre("Sergio")
		.primerApellido("Fuentes")
		.segundoApellido("Ortega")
		.fechaDeMatriculacion(LocalDate.of(2010,Month.SEPTEMBER, 1))
		.facultad(facultadService.getFacultad(1))
		.genero(Genero.HOMBRE)
		.build();
	
		Estudiante est12;
		Estudiante est22;

		est12 = Estudiante.builder()
		.nombre("Jorge")
		.primerApellido("Rodriguez")
		.segundoApellido("Plaza")
		.fechaDeMatriculacion(LocalDate.of(2020,Month.SEPTEMBER, 1))
		.facultad(facultadService.getFacultad(2))
		.genero(Genero.HOMBRE)
		.build();

		est22 = Estudiante.builder()
		.nombre("Marijose")
		.primerApellido("Hernandez")
		.segundoApellido("Sevilla")
		.fechaDeMatriculacion(LocalDate.of(2022,Month.SEPTEMBER, 1))
		.facultad(facultadService.getFacultad(2))
		.genero(Genero.MUJER)
		.build();


		Telefono t11 = Telefono.builder()
		.numero("630987654")
		.build();	
	
		Telefono t12 = Telefono.builder()
		.numero("699890098")
		.build();

		Telefono t21 = Telefono.builder()
		.numero("625598763")
		.build();
		
		Telefono t22 = Telefono.builder()
		.numero("625590076")
		.build();

		Correo c11 = Correo.builder()
		.direccion("aranchar@gmail.com")
		.build();

		Correo c21 = Correo.builder()
		.direccion("sfuentes@gmail.com")
		.build();

		Correo c12 = Correo.builder()
		.direccion("jrp@gmail.com")
		.build();
		
		Correo c22 = Correo.builder()
		.direccion("mjose@gmail.com")
		.build();

		
		c11.setEstudiante(est11);
		c12.setEstudiante(est11);
		c21.setEstudiante(est12);
		c22.setEstudiante(est12);


		t11.setEstudiante(est11);
		t12.setEstudiante(est11);
		t21.setEstudiante(est12);
		t22.setEstudiante(est12);

	List<Telefono> telefonosEst11 = Arrays.asList(t11,t12);
	est11.setTelefonos(telefonosEst11);

	List<Correo> correosEst11 = Arrays.asList(c11,c12);
	est11.setCorreos(correosEst11);

		estudianteService.persistirUpdateEstudiante(est11);
		estudianteService.persistirUpdateEstudiante(est21);
		estudianteService.persistirUpdateEstudiante(est12);
		estudianteService.persistirUpdateEstudiante(est22);

	}

}
