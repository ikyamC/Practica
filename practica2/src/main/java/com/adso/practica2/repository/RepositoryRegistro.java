package com.adso.practica2.repository;

import com.adso.practica2.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface RepositoryRegistro extends JpaRepository <Registro, Long> {

}
