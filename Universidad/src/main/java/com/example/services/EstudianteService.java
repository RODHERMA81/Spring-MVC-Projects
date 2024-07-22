package com.example.services;

import java.util.List;
import com.example.entities.Estudiante;

public interface EstudianteService {
    public List<Estudiante> getEstudiantes();
    public Estudiante getEstudiante(int idEstudiante);
    public void persistirUpdateEstudiante(Estudiante estudiante);
    public void deleteEstudiante(int idEstudiante);

}
