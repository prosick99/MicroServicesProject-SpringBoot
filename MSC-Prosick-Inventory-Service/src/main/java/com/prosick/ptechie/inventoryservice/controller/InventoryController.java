package com.prosick.ptechie.inventoryservice.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prosick.ptechie.inventoryservice.dto.InventoryResponse;
import com.prosick.ptechie.inventoryservice.servce.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory-service/")
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryService inventoryService;
	@GetMapping
	public List<InventoryResponse> isSKUAvailable(@RequestParam("sku-code") Set<String>  skuCodes) {
		return inventoryService.isSkuAvailable(skuCodes);
	}
	
	@GetMapping("/status")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String pingTest() {
		return "Checking if the Inventory service is up or not";
	}
}
