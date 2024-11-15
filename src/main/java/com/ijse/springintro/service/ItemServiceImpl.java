package com.ijse.springintro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.springintro.entity.Item;
import com.ijse.springintro.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    public  List<Item>getAllItems(){
        return itemRepository.findAll();
    }

    public Item getItemById(Long id){
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item updateItem(Long id,Item item){
        Item existingItem=itemRepository.findById(id).orElse(null);

        if(existingItem==null){
            return null;
        }else{
            existingItem.setName(item.getName());
            existingItem.setPrice(item.getPrice());
            existingItem.setDescription(item.getDescription());
            return itemRepository.save(item);
        }
    }

    @Override
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }
}
