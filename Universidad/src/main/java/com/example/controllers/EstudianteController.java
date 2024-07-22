package com.example.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.entities.Correo;
import com.example.entities.Estudiante;
import com.example.entities.Facultad;
import com.example.entities.Telefono;
import com.example.services.CorreoService;
import com.example.services.EstudianteService;
import com.example.services.FacultadService;
import com.example.services.TelefonoService;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping(value = "/estudiante", method = {RequestMethod.POST, RequestMethod.GET})
@RequiredArgsConstructor

public class EstudianteController {
    // private static final Logger LOG = Logger.getLogger("EstudianteController");

    private final EstudianteService estudianteService;
    private final FacultadService facultadService;
    private final CorreoService correoService;
    private final TelefonoService telefonoService;

@GetMapping("/all")
public String dameTodosLosEstudiantes(Model model){
    List<Estudiante> estudiantes = estudianteService.getEstudiantes();
    model.addAttribute("estudiantes", estudiantes);
    return "ListadoDeEstudiantes";

}


@GetMapping("frmAltaEstudiante")
public String formularioAltaEstudiante(Model model){
    Estudiante estudiante = new Estudiante();

    List<Facultad> facultades = facultadService.getFacultades();
    
    model.addAttribute("estudiante", estudiante);
    model.addAttribute("facultades", facultades);
        return "altaModificacionDeEstudiante";
}


@PostMapping("/persistirEstudiante")
public String persistirEstudiante(@ModelAttribute Estudiante estudiante,
    @RequestParam(name = "imagen", required = false) MultipartFile archivoDeImagen) {

        if (!archivoDeImagen.isEmpty()) {

    Path rutaRelativa = (Path) Paths.get("src\\main\\resources\\static\\imagenes\\");
    String rutaAbsoluta = ((java.nio.file.Path) rutaRelativa).toFile().getAbsolutePath();
    Path rutaCompleta = (Path) Paths.get(rutaAbsoluta + "\\" + archivoDeImagen.getOriginalFilename());

    
    try {
        byte[] archivoDeImagenEnBytes = archivoDeImagen.getBytes();
        Files.write(rutaCompleta, archivoDeImagenEnBytes);
    
        estudiante.setFoto(archivoDeImagen.getOriginalFilename());

    } catch (IOException e) {
        e.printStackTrace();
    }
        }
        estudianteService.persistirUpdateEstudiante(estudiante);
        return "redirect:/estudiante/all";

    }

    @GetMapping("/frmActualizar/{id}")
    public String formularioActualizacionEstudiante(@PathVariable(name = "id") int idEstudiante, Model model){

        Estudiante estudiante = estudianteService.getEstudiante(idEstudiante);
        List<Facultad> facultades = facultadService.getFacultades();

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("facultades", facultades);       

       return "altaModificacionDeEstudiante";

    }

    @GetMapping("/eliminarEstudiante/{id}")
    public String deleteEstudiante(@PathVariable(name = "id") int idEstudiante){

        estudianteService.deleteEstudiante(idEstudiante);

        return "redirect:/estudiante/all";
    }
    @GetMapping("/estudianteDetalles/{id}")
    public String estudianteoDetalles(@PathVariable(name = "id") int idEstudiante, Model model) {
        List<Correo> todosLosCorreos = correoService.getCorreos();
        List<Correo> correosDelEstudiante = todosLosCorreos.stream().filter(c -> c.getEstudiante().getId() == idEstudiante)
        .collect(Collectors.toList());
    
        model.addAttribute("correosDelEstudiante", correosDelEstudiante);
    
        List<Telefono> todosLosTelefonos = telefonoService.getTelefonos();
        List<Telefono> telefonosDelEstudiante = todosLosTelefonos.stream().filter(c -> c.getEstudiante().getId() == idEstudiante)
        .collect(Collectors.toList());
    
        model.addAttribute("telefonosDelEstudiante", telefonosDelEstudiante);
    
        Estudiante estudiante = estudianteService.getEstudiante(idEstudiante);    
            model.addAttribute("estudiante", estudiante);
        
        return "detallesDelEstudiante";
    }
}
