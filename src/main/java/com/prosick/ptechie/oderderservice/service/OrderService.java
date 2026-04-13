package com.prosick.ptechie.oderderservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.prosick.ptechie.oderderservice.dto.request.OderLineItemRequests;
import com.prosick.ptechie.oderderservice.dto.request.OderRequest;
import com.prosick.ptechie.oderderservice.dto.response.InventoryResponse;
import com.prosick.ptechie.oderderservice.entity.Oder;
import com.prosick.ptechie.oderderservice.entity.OrderLineItem;
import com.prosick.ptechie.oderderservice.repo.OderRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OderRepo oderRepo;
	private final InventoryClient inventoryClient;
	private final ModelMapper modelMapper;
	public void submitAOrder(OderRequest oderRequest) {
		
		List<OderLineItemRequests> itemRequests = oderRequest.getItems();
		
		List<OrderLineItem> lineItems = itemRequests.stream()
				.map(items -> orderItemRequestToOrderItems(items)).toList();
		
		
		//Here need to implement logic to make a record for 
		//How many quantity is requested for each SKU code
		Map<String, Integer> requestedSkuAndQuantity = new HashMap<>();
		for(OrderLineItem items : lineItems) {
			requestedSkuAndQuantity.put(items.getSkuCode(),
					requestedSkuAndQuantity.getOrDefault(items.getSkuCode(), 0)+items.getQuantity());
		}
		
		//Fetching all the SKUcode and store it inside a list
		Set<String> skuCodes = lineItems.stream()
				.map(items -> items.getSkuCode()).collect(Collectors.toSet());
		
		//This is called to make http request to Inventory Service
		List<InventoryResponse> responses = inventoryClient.checkInventory(skuCodes);

		//Below sections handles if adequate numbers of stock present on inventory or not
		
		boolean flag = true;

		for(InventoryResponse cur : responses) {
			if(cur.getQuantity() < requestedSkuAndQuantity.get(cur.getSkuCode())) {
				 flag = false;
				 break;
			}
		}
		

		if(flag && responses.size() == lineItems.size()) {
			
			Oder order = Oder.builder()
					.items(lineItems)
					.orderNumber(UUID.randomUUID().toString())
					.build();
			
			
			oderRepo.save(order);
		}
		
		else if(!flag){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"All product are not in stock");
		}
		else if(responses.size() != lineItems.size()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Some product are not found");
		}
	}

	private OrderLineItem orderItemRequestToOrderItems(OderLineItemRequests items) {
		OrderLineItem orderLineItem = modelMapper.map(items, OrderLineItem.class);
		return orderLineItem;
	}
}
