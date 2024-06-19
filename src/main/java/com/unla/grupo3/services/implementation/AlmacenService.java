package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Almacen;
import com.unla.grupo3.repositories.IAlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("almacenService")
public class AlmacenService {

    @Autowired
    private IAlmacenRepository repository;

    public AlmacenService(IAlmacenRepository repository) {
        this.repository = repository;
    }

    public Almacen findById(int id) {
        return repository.findById(id);
    }

}
