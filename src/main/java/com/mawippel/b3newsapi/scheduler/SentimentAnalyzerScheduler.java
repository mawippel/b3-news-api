package com.mawippel.b3newsapi.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.mawippel.b3newsapi.service.SentimentService;

@Configuration
@EnableScheduling
public class SentimentAnalyzerScheduler {

	private final long MINUTES = 60000;
	private final long executionRate = 15 * MINUTES;
	
	@Autowired
	private SentimentService sentimentService;

	@Scheduled(fixedRate = executionRate)
	public void execute() {
		System.out.println("Starting Task " + System.currentTimeMillis() / 1000);
//		sentimentService.analyzeNewsWithoutSentiment();
	}

}
