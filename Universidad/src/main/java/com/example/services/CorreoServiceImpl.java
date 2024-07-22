package com.example.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.dao.CorreoDao;
import com.example.entities.Correo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CorreoServiceImpl implements CorreoService {
private final CorreoDao correoDao;

@Override
public List<Correo> getCorreos() {
  return correoDao.findAll();
}

@Override
public Correo getCorreo(int idCorreo) {
  Optional<Correo> optionalCorreo = correoDao.findById(idCorreo);

  if (optionalCorreo.isPresent())
  return optionalCorreo.get();
  else
      return null;

}

@Override
public void persistirCorreo(Correo correo) {
  correoDao.save(correo);
}


}