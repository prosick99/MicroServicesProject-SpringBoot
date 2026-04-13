package com.prosick.ptechie.oderderservice.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderLineItemResponse {

    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
