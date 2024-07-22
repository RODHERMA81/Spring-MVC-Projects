package com.example.services;

import java.util.List;
import com.example.entities.Telefono;


public interface TelefonoService {
public List<Telefono> getTelefonos();
public Telefono getTelefono(int idTelefono);
public void persistirTelefono(Telefono telefono);  
}

