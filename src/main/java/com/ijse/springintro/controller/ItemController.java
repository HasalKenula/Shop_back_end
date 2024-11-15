package com.ijse.springintro.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.springintro.dto.ItemReqDto;
import com.ijse.springintro.entity.Category;
import com.ijse.springintro.entity.Item;
import com.ijse.springintro.service.CategoryService;
import com.ijse.springintro.service.ItemService;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/item")
    public ResponseEntity<?> createItem(@RequestBody ItemReqDto itemReqDto){
        try {
            Item newItem=new Item();
            newItem.setName(itemReqDto.getName());
            newItem.setPrice(itemReqDto.getPrice());
            newItem.setDescription(itemReqDto.getDescription());

            Category category =categoryService.getCategoryById(itemReqDto.getCategoryId());
            newItem.setCategory(category);
            
            Item item=itemService.createItem(newItem);
            return ResponseEntity.status(201).body(item);
        } catch (Exception e) {
            return ResponseEntity.status(201).body(e.getMessage());
        }
    }

    @GetMapping("/item")
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> itemList=itemService.getAllItems();
        return ResponseEntity.status(200).body(itemList);
    }

    @PutMapping("item/{itemId}")
    public ResponseEntity<Item> updateItem(@RequestBody ItemReqDto itemReqDto, @PathVariable Long itemId){
        try {
            Item existingItem=itemService.getItemById(itemId);
            if(existingItem!=null){
                existingItem.setName(itemReqDto.getName());
                existingItem.setPrice(itemReqDto.getPrice());
                existingItem.setDescription(itemReqDto.getDescription());

                Category category=categoryService.getCategoryById(itemReqDto.getCategoryId());
                existingItem.setCategory(category);

                Item updaItems=itemService.updateItem(itemId, existingItem);
                return ResponseEntity.status(200).body(updaItems);
            }else{
                return ResponseEntity.status(201).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("item/{itemId}")
    public void deleteItem(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
    }

}
