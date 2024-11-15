package com.ijse.springintro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.springintro.entity.Stock;

@Service
public interface StockService {
    List<Stock> getAllStock();
    Stock createStock(Stock stock);
    Stock getStockById(Long id);
    Stock updateStock(Long id,Stock stock);
    void deleteStock(Long id);
}
