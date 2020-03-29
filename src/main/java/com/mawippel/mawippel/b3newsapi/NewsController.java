package com.mawippel.mawippel.b3newsapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;

@RestController
public class NewsController {

	@GetMapping("/")
	DetectSentimentResult all() {
		String text = "It is raining so bad today in Seattle";

		AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();

		AmazonComprehend comprehendClient = AmazonComprehendClientBuilder.standard().withCredentials(awsCreds)
				.withRegion("us-east-2").build();

		System.out.println("Calling DetectSentiment");
		DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest().withText(text)
				.withLanguageCode("en");
		DetectSentimentResult detectSentimentResult = comprehendClient.detectSentiment(detectSentimentRequest);
		return detectSentimentResult;
	}

}
