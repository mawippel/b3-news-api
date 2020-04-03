package com.mawippel.b3newsapi.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.mawippel.b3newsapi.model.NewsEntity;

public interface NewsRepository extends CrudRepository<NewsEntity, UUID> {

	Iterable<NewsEntity> findByPositiveIsNull();

}
