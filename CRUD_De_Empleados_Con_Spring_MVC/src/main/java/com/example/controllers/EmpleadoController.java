package com.example.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;


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

import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/empleado", method = {RequestMethod.POST, RequestMethod.GET})
@RequiredArgsConstructor

public class EmpleadoController {

    private static final Logger LOG = Logger.getLogger("EmpleadoController");

    private final EmpleadoService empleadoService;
    private final DepartamentoService departamentoService;

    // Ahora necesito un método donde se delega la petición de mostrar todos los
    // empleados, de la tabla
    // empleados. Este método devuelve un String que es el nombre de la vista que se
    // mostrará al cliente en el
    // navegador.
    @GetMapping("/all")
    public String dameTodosLosEmpleados(Model model) {

        List<Empleado> empleados = empleadoService.getEmpleados();

        model.addAttribute("empleados", empleados);

        return "ListadoDeEmpleados";
    }

    // Método que muestra el formulario de alta de empleado

    @GetMapping("/frmAltaEmpleado")
    public String formularioAltaEmpleado(Model model) {

        // Hay que enviar a la vista un objeto empleado vacio para que se vincule con
        // los controles del formulario.
        Empleado empleado = new Empleado();
        // También hay que enviar los departamentos

        List<Departamento> departamentos = departamentoService.getDepartamentos();

        model.addAttribute("empleado", empleado);
        model.addAttribute("departamentos", departamentos);

        return "altaModificacionDeEmpleado";
    }

    // Método que recibe por POST los datos de los controles del formulario de alta de empleado.
    @PostMapping("/persistirEmpleado")
    public String persistirEmpleado(@ModelAttribute Empleado empleado,
    @RequestParam(name = "imagen", required = false) MultipartFile archivoDeImagen) {

        if (!archivoDeImagen.isEmpty()) {
         
    // Ahora necesito la ruta relativa de la carpeta donde se va a almacenar la foto
    Path rutaRelativa = Paths.get("src\\main\\resources\\static\\imagenes\\");

    // Ahora necesitamos la ruta absoluta,es decir,la ruta de verdad,todas las carpetas que hay que atravesar para llegar a esa imagen,
    // donde yo haya puesto el servidor.
    String rutaAbsoluta = rutaRelativa.toFile().getAbsolutePath();

    // Y finalmente, la ruta completa
    Path rutaCompleta = Paths.get(rutaAbsoluta + "\\" + archivoDeImagen.getOriginalFilename());

    // Lo ultimo que falta es manejar la imagen(archivoDeImagen)
    try {
        byte[] archivoDeImagenEnBytes = archivoDeImagen.getBytes();
        Files.write(rutaCompleta,archivoDeImagenEnBytes);
    // Ahora hay que establecer (setter) el nombre de la imagen recibida a la propiedad foto del empleado
        empleado.setFoto(archivoDeImagen.getOriginalFilename());

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

        }
        empleadoService.persistirUpdateEmpleado(empleado);

        return "redirect:/empleado/all";

    }


    // Método que muestra el formulario de actualización de un empleado

    @GetMapping("/frmActualizar/{id}")
    public String formularioActualizacionEmpleado(@PathVariable(name = "id") int idEmpleado, Model model){

        // Con el id del Empleado (idEmpleado), solicitamos al servicio de empleado que nos
        // recupere el empleado correspondiente

        Empleado empleado = empleadoService.getEmpleado(idEmpleado);
        List<Departamento> departamentos = departamentoService.getDepartamentos();

        model.addAttribute("empleado", empleado);
        model.addAttribute("departamentos", departamentos);

       

       return "altaModificacionDeEmpleado";

    }


    // Método para eliminar un empleado
    @GetMapping("/eliminarEmpleado/{id}")
    public String deleteEmpleado(@PathVariable(name = "id") int idEmpleado){

        empleadoService.deleteEmpleado(idEmpleado);

        return "redirect:/empleado/all";
    }

    // Método que muestra los DETALLES del empleado, en éste caso, de detalles solo existe la foto
    @GetMapping("/empleadoDetalles/{id}")
    public String empleadoDetalles(@PathVariable(name = "id") int idEmpleado, Model model) {

        Empleado empleado = empleadoService.getEmpleado(idEmpleado);
        model.addAttribute("empleado", empleado);

        return "detallesDelEmpleado";
    }
}
