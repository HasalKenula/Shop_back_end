package com.ijse.springintro.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockReqDto {
    private Long qty;
    private Long itemId;
}
