package com.mawippel.b3newsapi.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.mawippel.b3newsapi.service.SentimentService;

@Configuration
@EnableScheduling
public class NewsSentimentAnalyzerScheduler implements SchedulerOptions {

	@Autowired
	private SentimentService sentimentService;

	@Scheduled(fixedRate = executionRate)
	public void execute() {
		analyzeNews();
	}

	private void analyzeNews() {
		System.out.println("Iniciando análise das notícias: " + System.currentTimeMillis() / 1000);
		int analyzedNews = sentimentService.analyzeNewsWithoutSentiment();
		System.out.printf("Número de notícias analisadas: %s\n", analyzedNews);
		System.out.println("Análise das notícias finalizada.");
	}

}
