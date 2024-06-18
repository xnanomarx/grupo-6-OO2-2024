package com.unla.grupo3.repositories;

import com.unla.grupo3.entities.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable> {

}
