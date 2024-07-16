package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.EmpleadoDao;
import com.example.entities.Empleado;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService{

// Se necesita inyectar el objeto DAO, es decir, una Inyección de Dependencia
// DI(Dependency Injection) de EmpleadoDao, porque los métodos de la capa de servicios
// se van a implementar a partir de los métodos de la capa DAO.

// ¿COMO SE INYECTA UNA DEPENDENCIA?
// Rta.
// VARIANTE # 1. De forma automatica, utilizando la anotación @Autowired

// VARIANTE # 2. Por constructor, que es la variante recomendada actualmente

private final EmpleadoDao empleadoDao;

    @Override
    public List<Empleado> getEmpleados() {
       return empleadoDao.findAll(); 
       
    }

    @Override
    public Empleado getEmpleado(int idEmpleado) {
        Optional<Empleado> optionalEmpleado = empleadoDao.findById(idEmpleado);
         
        if(optionalEmpleado.isPresent())
            return optionalEmpleado.get();
        else
        return null;
    }

    @Override
    public void persistirUpdateEmpleado(Empleado empleado) {
        empleadoDao.save(empleado);
    }

    @Override
    public void deleteEmpleado(int idEmpleado) {
        empleadoDao.deleteById(idEmpleado);
    }

}
