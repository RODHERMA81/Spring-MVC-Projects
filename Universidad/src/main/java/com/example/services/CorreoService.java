package com.example.services;

import java.util.List;
import com.example.entities.Correo;


public interface CorreoService {

public List<Correo> getCorreos();
public Correo getCorreo(int idCorreo);
public void persistirCorreo(Correo correo);  
}
    
   
   

