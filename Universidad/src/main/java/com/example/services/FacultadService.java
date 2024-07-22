package com.example.services;

import java.util.List;
import com.example.entities.Facultad;

public interface FacultadService {
    public List<Facultad> getFacultades();
    public Facultad getFacultad(int idFacultad);
    public void persistirUpdateFacultad(Facultad facultad);
    public void deleteFacultad(int idFacultad);
}
