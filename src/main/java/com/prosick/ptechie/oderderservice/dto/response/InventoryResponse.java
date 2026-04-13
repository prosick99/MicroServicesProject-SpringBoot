package com.prosick.ptechie.oderderservice.dto.response;

import lombok.Data;

@Data
public class InventoryResponse {
	
	private Long id;
	private String skuCode;
	private int quantity;
}
