package com.prosick.ptechie.inventoryservice.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prosick.ptechie.inventoryservice.dto.InventoryResponse;
import com.prosick.ptechie.inventoryservice.entity.Inventory;


@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

	List<InventoryResponse> findBySkuCodeIn(Set<String> skuCode);
	
}
