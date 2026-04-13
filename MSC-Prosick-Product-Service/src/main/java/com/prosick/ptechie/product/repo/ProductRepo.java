package com.prosick.ptechie.product.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.prosick.ptechie.product.entity.Product;

@Repository
public interface ProductRepo extends MongoRepository<Product, String>{

}
