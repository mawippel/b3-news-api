package com.mawippel.b3newsapi.service;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;

@Service
public class AwsComprehendService {

	public DetectSentimentResult detectSentiment(String text) {
		AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();
		AmazonComprehend comprehendClient = AmazonComprehendClientBuilder.standard().withCredentials(awsCreds)
				.withRegion("us-east-2").build();
		DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest().withText(text)
				.withLanguageCode("pt");
		DetectSentimentResult detectSentimentResult = comprehendClient.detectSentiment(detectSentimentRequest);
		return detectSentimentResult;
	}

}
