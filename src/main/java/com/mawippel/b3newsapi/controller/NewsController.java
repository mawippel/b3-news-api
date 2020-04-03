package com.mawippel.b3newsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.mawippel.b3newsapi.model.NewsEntity;
import com.mawippel.b3newsapi.repository.NewsRepository;

@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsRepository newsRepository;

	@GetMapping("/sentiment")
	DetectSentimentResult all() {
		String text = "Dólar abre em queda com Senado dos EUA e MP de Bolsonaro no radar";

		AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();

		AmazonComprehend comprehendClient = AmazonComprehendClientBuilder.standard().withCredentials(awsCreds)
				.withRegion("us-east-2").build();

		System.out.println("Calling DetectSentiment");
		DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest().withText(text)
				.withLanguageCode("pt");
		DetectSentimentResult detectSentimentResult = comprehendClient.detectSentiment(detectSentimentRequest);
		return detectSentimentResult;
	}

	@GetMapping
	Iterable<NewsEntity> listNews() {
		return newsRepository.findAll();
	}

}
