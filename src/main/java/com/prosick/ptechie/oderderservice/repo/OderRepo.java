package com.prosick.ptechie.oderderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prosick.ptechie.oderderservice.entity.Oder;

@Repository
public interface OderRepo extends JpaRepository<Oder, Long>{

}
