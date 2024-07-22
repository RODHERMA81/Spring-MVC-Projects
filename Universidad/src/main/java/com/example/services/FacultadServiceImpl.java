package com.example.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.dao.FacultadDao;
import com.example.entities.Facultad;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacultadServiceImpl implements FacultadService {
    private final FacultadDao facultadDao;

    @Override
    public List<Facultad> getFacultades() {
      return facultadDao.findAll();
    }

    @Override
    public Facultad getFacultad(int idFacultad) {
       Optional<Facultad> optionalFacultad = facultadDao.findById(idFacultad);

       if(optionalFacultad.isPresent())
        return optionalFacultad.get();
       else
       return null;
    }

    @Override
    public void persistirUpdateFacultad(Facultad facultad) {
      facultadDao.save(facultad);
    }

    @Override
    public void deleteFacultad(int idFacultad) {
      facultadDao.deleteById(idFacultad);
    }


    
   

}
