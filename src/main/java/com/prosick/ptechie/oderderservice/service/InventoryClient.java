package com.prosick.ptechie.oderderservice.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.prosick.ptechie.oderderservice.dto.request.OderRequest;
import com.prosick.ptechie.oderderservice.dto.response.InventoryResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class InventoryClient {

	private final WebClient.Builder webClientBuilder;
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallBackMethod")
	public List<InventoryResponse> checkInventory(Set<String> skuCodes){
		
		List<InventoryResponse> inventoryResponses = webClientBuilder.build()
			.get()
			.uri(uriBuilder -> uriBuilder
					.scheme("http")
					.host("INVENTORY-SERVICE")
					.path("/api/inventory-service/")
					.queryParam("sku-code", skuCodes)
					.build())
			.retrieve()
			.bodyToFlux(InventoryResponse.class)
			.collectList()
			.block();
		return inventoryResponses;
	}
	
	public List<InventoryResponse> fallBackMethod(Set<String> skuCodes, Throwable t) {

	    System.out.println("Fallback triggered: " + t.getMessage());

	    return List.of(); // empty response
	}		
}
