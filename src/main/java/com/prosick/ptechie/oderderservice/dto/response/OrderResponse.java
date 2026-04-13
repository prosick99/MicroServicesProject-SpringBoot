package com.prosick.ptechie.oderderservice.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class OrderResponse {

	private long id;
	private String orderNumber;
	private List<OrderLineItemResponse> items;
}
