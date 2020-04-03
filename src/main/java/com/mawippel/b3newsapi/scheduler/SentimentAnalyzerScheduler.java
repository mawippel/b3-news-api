package com.mawippel.b3newsapi.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SentimentAnalyzerScheduler {
	
	private final long MINUTES = 60000;	
	private final long executionRate = 15 * MINUTES;

	@Scheduled(fixedRate = executionRate)
	public void scheduleFixedRateTask() {
	    System.out.println(
	      "Fixed rate task - " + System.currentTimeMillis() / 1000);
	}
	
}
