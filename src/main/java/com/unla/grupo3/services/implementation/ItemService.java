package com.unla.grupo3.services.implementation;

import com.unla.grupo3.repositories.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemService {

    @Autowired
    private IItemRepository itemRepository;
}
