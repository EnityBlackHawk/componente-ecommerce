package com.example.componenteecommerce.service;

import com.example.componenteecommerce.dto.ItemDTO;
import com.example.componenteecommerce.entity.Item;
import com.example.componenteecommerce.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends GenericService<Item, Long, ItemRepository, ItemDTO, ItemDTO> {

    protected ItemService(ItemRepository repository) {
        super(repository, Item.class, ItemDTO.class, ItemDTO.class);
    }

}
