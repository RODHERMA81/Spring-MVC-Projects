package com.example.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.dao.EstudianteDao;
import com.example.entities.Estudiante;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService{

 private final EstudianteDao estudianteDao;

@Override
public List<Estudiante> getEstudiantes() {
    return estudianteDao.findAll();
}

@Override
public Estudiante getEstudiante(int idEstudiante) {
    Optional<Estudiante> optionalEstudiante = estudianteDao.findById(idEstudiante);

    if (optionalEstudiante.isPresent()) 
        return optionalEstudiante.get();
        else
        return null;
    }


@Override
public void persistirUpdateEstudiante(Estudiante estudiante) {
    estudianteDao.save(estudiante);
}

@Override
public void deleteEstudiante(int idEstudiante) {
   estudianteDao.deleteById(idEstudiante);
}   
}
