package com.mawippel.b3newsapi.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.mawippel.b3newsapi.model.ParagraphEntity;

public interface ParagraphRepository extends CrudRepository<ParagraphEntity, UUID> {

}
