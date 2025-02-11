package com.mawippel.b3newsapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mawippel.b3newsapi.model.ParagraphEntity;

public interface ParagraphRepository extends JpaRepository<ParagraphEntity, UUID> {

	List<ParagraphEntity> findByPositiveIsNull();
	
	List<ParagraphEntity> findBySentimentIsNull();
	
	List<ParagraphEntity> findByNewsId(UUID id);
	
	@Transactional
	void deleteByTextIn(List<String> texts);
	
}
