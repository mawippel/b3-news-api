package com.mawippel.b3newsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mawippel.b3newsapi.model.ParagraphEntity;
import com.mawippel.b3newsapi.model.StockEntity;
import com.mawippel.b3newsapi.repository.NewsRepository;
import com.mawippel.b3newsapi.repository.ParagraphRepository;
import com.mawippel.b3newsapi.repository.StockRepository;

@Service
public class ParagraphService {
	
	@Autowired
	private ParagraphRepository paragraphRepo;
	
	@Autowired
	private NewsRepository newsRepo;
	
	@Autowired
	private StockRepository stockRepo;
	
	@Transactional
	public void findAndSaveQuotedStocks() {
		List<StockEntity> allStocks = stockRepo.findAll();
		List<ParagraphEntity> allParagraphs = paragraphRepo.findAll();
		for (ParagraphEntity paragraph : allParagraphs) {
			for (StockEntity stock : allStocks) {				
				if (paragraph.getText().toUpperCase().contains(stock.getTicker())) {
					paragraph.getNews().getStocks().add(stock);
				}
			}
			newsRepo.save(paragraph.getNews());
		}
	}

}
