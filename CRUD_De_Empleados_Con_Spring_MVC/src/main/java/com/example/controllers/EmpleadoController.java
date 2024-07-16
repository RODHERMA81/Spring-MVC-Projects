package com.example.controllers;

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

    // Método que recibe por POST los datos de los controles del formulario de alta de empleado
    @PostMapping("/persistirEmpleado")
    public String persistirEmpleado(@ModelAttribute Empleado empleado) {

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

        return "persistirEmpleado";


    }

}
