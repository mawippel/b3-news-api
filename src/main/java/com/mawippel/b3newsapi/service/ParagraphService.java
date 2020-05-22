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
				Pattern namePattern = getNameRegexPattern(stock);
				Pattern companyPattern = getCompanyRegexPattern(stock);

				String upperCaseParagraph = paragraph.getText().toUpperCase();
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

	private Pattern getNameRegexPattern(StockEntity stock) {
		return Pattern.compile("((^)|(\\s+)|(\\.+)|(!)|(,)|(\\?)|(;)|$)" + stock.getName().toUpperCase()
				+ "((^)|(\\s+)|(\\.+)|(!)|(,)|(\\?)|(;)|$)");
	}

	private Pattern getCompanyRegexPattern(StockEntity stock) {
		return Pattern.compile("((^)|(\\s+)|(\\.+)|(!)|(,)|(\\?)|(;)|$)" + stock.getCompany().toUpperCase()
				+ "((^)|(\\s+)|(\\.+)|(!)|(,)|(\\?)|(;)|$)");
	}

}
