package com.mawippel.b3newsapi.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.mawippel.b3newsapi.model.ParagraphEntity;
import com.mawippel.b3newsapi.model.Sentiment;
import com.mawippel.b3newsapi.repository.NewsRepository;
import com.mawippel.b3newsapi.repository.ParagraphRepository;

@Service
public class SentimentService {

	@Autowired
	private NewsRepository newsRepo;

	@Autowired
	private ParagraphRepository paragraphRepo;

	@Autowired
	private AwsComprehendService awsComprehendService;

	/**
	 * Analyze the sentiment of News that weren't analyzed
	 * 
	 * @return the number of analyzed news
	 */
	public int analyzeNewsWithoutSentiment() {
		var newsWithoutSentiment = newsRepo.findByPositiveIsNull();
		newsWithoutSentiment.forEach(news -> {
			DetectSentimentResult detectedSentiment = awsComprehendService.detectSentiment(news.getTitle());
			news.setPositive(detectedSentiment.getSentimentScore().getPositive());
			news.setNeutral(detectedSentiment.getSentimentScore().getNeutral());
			news.setNegative(detectedSentiment.getSentimentScore().getNegative());
			news.setMixed(detectedSentiment.getSentimentScore().getMixed());
			newsRepo.save(news);
		});
		return newsWithoutSentiment.size();
	}

	/**
	 * Analyze the sentiment of Paragraphs that weren't analyzed
	 * 
	 * @return the number of analyzed paragrahps
	 */
	public int analyzeParagraphsWithoutSentiment() {
		var paragraphsWithoutSentiment = paragraphRepo.findByPositiveIsNull();
		paragraphsWithoutSentiment.forEach(paragraph -> {
			DetectSentimentResult detectedSentiment = awsComprehendService.detectSentiment(paragraph.getText());
			paragraph.setPositive(detectedSentiment.getSentimentScore().getPositive());
			paragraph.setNeutral(detectedSentiment.getSentimentScore().getNeutral());
			paragraph.setNegative(detectedSentiment.getSentimentScore().getNegative());
			paragraph.setMixed(detectedSentiment.getSentimentScore().getMixed());
			paragraphRepo.save(paragraph);
		});
		return paragraphsWithoutSentiment.size();
	}

	/**
	 * Analyze the overall sentiment of Paragraphs that weren't analyzed
	 * 
	 * @return the number of analyzed paragraphs
	 */
	public int analyzeOverallParagraphsSentiment() {
		var paragraphsWithoutOverallSentiment = paragraphRepo.findBySentimentIsNull();
		paragraphsWithoutOverallSentiment.forEach(paragraph -> {
			Float positive = paragraph.getPositive();
			Float negative = paragraph.getNegative();
			Sentiment overallSentiment = Sentiment.getSentimentByIndex(positive, negative);
			paragraph.setSentiment(overallSentiment);
			paragraphRepo.save(paragraph);
		});
		return paragraphsWithoutOverallSentiment.size();
	}

	public int analyzeOverallNewsSentiment() {
		var newsWithoutOverallSentiment = newsRepo.findBySentimentIsNull();
		newsWithoutOverallSentiment.forEach(news -> {
			Float positive = news.getPositive();
			Float negative = news.getNegative();
			Sentiment overallSentiment = Sentiment.getSentimentByIndex(positive, negative);
			news.setSentiment(overallSentiment);
			newsRepo.save(news);
		});
		return newsWithoutOverallSentiment.size();
	}

	public void deleteAllRepeatedParagraphs() {
		List<String> collectedTexts = paragraphRepo.findAll().stream()
				.collect(Collectors.groupingBy(ParagraphEntity::getText, Collectors.counting())).entrySet().stream()
				.filter(x -> x.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
		paragraphRepo.deleteByTextIn(collectedTexts);
	}

}
