package com.mawippel.b3newsapi.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
				//TODO extrair o build dos patterns pra outro metodo
				String namePatternString = "((^)|(\\s+)|(\\.+)|(!)|(,)|(\\?)|(;)|$)" + stock.getName().toUpperCase()
						+ "((^)|(\\s+)|(\\.+)|(!)|(,)|(\\?)|(;)|$)";
				String companyPatternString = "((^)|(\\s+)|(\\.+)|(!)|(,)|(\\?)|(;)|$)"
						+ stock.getCompany().toUpperCase() + "((^)|(\\s+)|(\\.+)|(!)|(,)|(\\?)|(;)|$)";

				String upperCaseParagraph = paragraph.getText().toUpperCase();
				Pattern namePattern = Pattern.compile(namePatternString);
				Pattern companyPattern = Pattern.compile(companyPatternString);
				
				if (paragraph.getText().toUpperCase().contains(stock.getTicker())
						|| namePattern.matcher(upperCaseParagraph).find()
						|| companyPattern.matcher(upperCaseParagraph).find()) {
					paragraph.getNews().getStocks().add(stock);
				}
			}
			newsRepo.save(paragraph.getNews());
		}
	}
	
	public void deleteAllRepeatedParagraphs() {
		List<String> collectedTexts = paragraphRepo.findAll().stream()
				.collect(Collectors.groupingBy(ParagraphEntity::getText, Collectors.counting())).entrySet().stream()
				.filter(x -> x.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
		paragraphRepo.deleteByTextIn(collectedTexts);
	}

}
