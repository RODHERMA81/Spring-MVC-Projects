package com.example.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.dao.TelefonoDao;
import com.example.entities.Telefono;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TelefonoServiceImpl implements TelefonoService {
    
    private final TelefonoDao telefonoDao;

    @Override
    public List<Telefono> getTelefonos() {
        return telefonoDao.findAll(); 
   
    }

    @Override
    public Telefono getTelefono(int idTelefono) {
        Optional<Telefono> optionalTelefono = telefonoDao.findById(idTelefono);
        if(optionalTelefono.isPresent())
        return optionalTelefono.get();
        else
            return null;

     }

    @Override
    public void persistirTelefono(Telefono telefono) {
        telefonoDao.save(telefono);  
    }

    
  
}
