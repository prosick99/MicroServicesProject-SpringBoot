package com.prosick.ptechie.inventoryservice.servce;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.prosick.ptechie.inventoryservice.dto.InventoryResponse;
import com.prosick.ptechie.inventoryservice.repo.InventoryRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepo inventoryRepo;

	@Transactional
	public List<InventoryResponse> isSkuAvailable(Set<String> skuCode) {
		
		return inventoryRepo.findBySkuCodeIn(skuCode)
				.stream().map(items -> InventoryResponse.builder()
						.id(items.getId())
						.quantity(items.getQuantity())
						.skuCode(items.getSkuCode())
						.build())
				.toList();
	}
}
