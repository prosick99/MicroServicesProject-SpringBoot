package com.prosick.ptechie.oderderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prosick.ptechie.oderderservice.dto.request.OderRequest;
import com.prosick.ptechie.oderderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-service/")
public class OderController {

	private final OrderService orderService;
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	
	public String requestAOrder(@RequestBody OderRequest oderRequest) {
		
//		if(oderRequest.getItems() == null)
//			return "You have not added ant items, Please add...";
		orderService.submitAOrder(oderRequest);
		
		return "Request submitted successfully";
		}
	
	@GetMapping("status")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String pingTest() {
		return "Checking if the order service is up or not";
	}
	
	
}
