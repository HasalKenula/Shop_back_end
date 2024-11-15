package com.ijse.springintro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.springintro.entity.Stock;
import com.ijse.springintro.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
   private StockRepository stockRepository;

    public  Stock createStock(Stock stock){
        return stockRepository.save(stock);
    } 

   public List<Stock> getAllStock(){
    return stockRepository.findAll();
   }

   public Stock  getStockById(Long id){
    return stockRepository.findById(id).orElse(null);
   }

   public Stock updateStock(Long id,Stock stock){
    Stock existingStock=stockRepository.findById(id).orElse(null);

    if(existingStock==null){
        return null;
    }else{
        existingStock.setQty(stock.getQty());
        return stockRepository.save(stock);
    }
   }

   public void deleteStock(Long id){
    stockRepository.deleteById(id);
   }
}
