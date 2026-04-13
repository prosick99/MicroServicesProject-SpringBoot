package com.prosick.ptechie.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prosick.ptechie.product.dto.ProductRequest;
import com.prosick.ptechie.product.dto.ProductResponse;
import com.prosick.ptechie.product.entity.Product;
import com.prosick.ptechie.product.repo.ProductRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductRepo productRepo;
	
	public void createProduct(ProductRequest productRequest) {
		
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		
		productRepo.save(product);
		log.info("Product {} is saved", product.getId());
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepo.findAll();
		
		return products.stream().map(product -> mapToProductResponse(product)).toList();
	}

	private ProductResponse mapToProductResponse(Product product) {
		ProductResponse productResponse = ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
		return productResponse;
	}
}
