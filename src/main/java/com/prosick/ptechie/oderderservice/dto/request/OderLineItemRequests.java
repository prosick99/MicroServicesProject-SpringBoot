package com.prosick.ptechie.oderderservice.dto.request;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class OderLineItemRequests {

    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
