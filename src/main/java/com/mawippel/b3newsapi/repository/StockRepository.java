package com.mawippel.b3newsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mawippel.b3newsapi.model.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, String> {

}
