package com.mawippel.b3newsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.mawippel.b3newsapi.model.NewsEntity;
import com.mawippel.b3newsapi.repository.NewsRepository;

@Service
public class SentimentService {

	@Autowired
	private NewsRepository newsRepo;

	@Autowired
	private AwsComprehendService awsComprehendService;

	public void analyzeNewsWithoutSentiment() {
		Iterable<NewsEntity> newsWithoutSentiment = newsRepo.findByPositiveIsNull();
		for (NewsEntity newsEntity : newsWithoutSentiment) {
			DetectSentimentResult detectedSentiment = awsComprehendService.detectSentiment(newsEntity.getTitle());
			newsEntity.setPositive(detectedSentiment.getSentimentScore().getPositive());
			newsEntity.setNeutral(detectedSentiment.getSentimentScore().getNeutral());
			newsEntity.setNegative(detectedSentiment.getSentimentScore().getNegative());
			newsEntity.setMixed(detectedSentiment.getSentimentScore().getMixed());
			newsRepo.save(newsEntity);
		}
	}

}
