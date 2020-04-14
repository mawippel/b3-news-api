package com.mawippel.b3newsapi.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.mawippel.b3newsapi.service.SentimentService;

@Configuration
@EnableScheduling
public class ParagraphSentimentAnalyzerScheduler implements SchedulerOptions {

	@Autowired
	private SentimentService sentimentService;

	@Scheduled(fixedRate = executionRate)
	public void execute() {
		sentimentService.deleteAllRepeatedParagraphs();
		analyzeParagraphs();
	}

	private void analyzeParagraphs() {
		System.out.println("Iniciando análise dos parágrafos: " + System.currentTimeMillis() / 1000);
		int analyzedNews = sentimentService.analyzeParagraphsWithoutSentiment();
		sentimentService.analyzeOverallParagraphsSentiment();
		System.out.printf("Número de parágrafos analisados: %s\n", analyzedNews);
		System.out.println("Análise dos parágrafos finalizada.");
	}

}
