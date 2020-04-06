package com.mawippel.b3newsapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mawippel.b3newsapi.model.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, UUID> {

	List<NewsEntity> findByPositiveIsNull();

}
