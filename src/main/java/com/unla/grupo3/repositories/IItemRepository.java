package com.unla.grupo3.repositories;

import com.unla.grupo3.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("itemRepository")
public interface IItemRepository extends JpaRepository<Item, Serializable> {

}
