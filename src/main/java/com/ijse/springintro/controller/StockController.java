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

import com.ijse.springintro.dto.StockReqDto;
import com.ijse.springintro.entity.Item;
import com.ijse.springintro.entity.Stock;
import com.ijse.springintro.service.ItemService;
import com.ijse.springintro.service.StockService;

@RestController
@CrossOrigin(origins = "*")
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/stock")
    public ResponseEntity<List<Stock>> getAllStock(){
        List<Stock> stocks=stockService.getAllStock();
        return ResponseEntity.status(200).body(stocks);
    }

    @PostMapping("/stock")
    public ResponseEntity<?> createStock(@RequestBody StockReqDto stockReqDto){
        try {
            Stock newStock=new Stock();
            newStock.setQty(stockReqDto.getQty());

            Item item=itemService.getItemById(stockReqDto.getItemId());
            newStock.setItem(item);

            Stock stock=stockService.createStock(newStock);
            return ResponseEntity.status(201).body(stock);
        } catch (Exception e) {
            return ResponseEntity.status(201).body(e.getMessage());
        }
    }

    @PutMapping("/stock/{stockId}")
    public ResponseEntity<Stock> updateStock(@RequestBody StockReqDto stockReqDto,@PathVariable Long stockId){
        try {
            Stock existingStock=stockService.getStockById(stockId);
            if(existingStock!=null){
                existingStock.setQty(stockReqDto.getQty());
                Item item=itemService.getItemById(stockReqDto.getItemId());
                existingStock.setItem(item);

                Stock updateStock=stockService.updateStock(stockId,existingStock);
                return ResponseEntity.status(200).body(updateStock);
            }else{
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/stock/{stockId}")
    public void deleteStock(@PathVariable Long stockId){
        stockService.deleteStock(stockId);
    }
}
