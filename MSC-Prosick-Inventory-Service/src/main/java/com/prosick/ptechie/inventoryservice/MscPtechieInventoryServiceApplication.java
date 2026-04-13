package com.prosick.ptechie.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.prosick.ptechie.inventoryservice.entity.Inventory;
import com.prosick.ptechie.inventoryservice.repo.InventoryRepo;

@SpringBootApplication
public class MscPtechieInventoryServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(MscPtechieInventoryServiceApplication.class, args);
		
	}

	@Bean
	public CommandLineRunner runner(InventoryRepo inventoryRepo) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("i Phone 13");
			inventory.setQuantity(13);
			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("i Phone 13 Prox Max");
			inventory2.setQuantity(0);
			
			if(inventoryRepo.count() == 0) {
				inventoryRepo.save(inventory);
				inventoryRepo.save(inventory2);
			}
		};
	}
}
