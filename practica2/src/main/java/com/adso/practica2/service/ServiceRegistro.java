package com.adso.practica2.service;

import com.adso.practica2.model.Registro;
import com.adso.practica2.repository.RepositoryRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRegistro {
    @Autowired
    private RepositoryRegistro repositoryRegister;

    //Obtener todos los registros
    public List<Registro> GetAllRegistro(){
        return repositoryRegister.findAll();
    }

    public Optional<Registro> getRegistro(Long id){
        return repositoryRegister.findById(id);
    }

    //Guardar un registro
    public Registro saveRegister(Registro registro){
        return repositoryRegister.save(registro);
    }

    //Editar un registro
    public void updateRegister(Registro registro){
        repositoryRegister.save(registro);
    }

    //Borrar registro
    public void deleteRegister(Long id){
        repositoryRegister.deleteById(id);
    }
}
