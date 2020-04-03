package com.mawippel.b3newsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.mawippel.b3newsapi.service.AwsComprehendService;

@RestController
@RequestMapping("/sentiment")
public class SentimentController {

	@Autowired
	private AwsComprehendService awsComprehendService;

	@GetMapping
	DetectSentimentResult all() {
		String text = "Dólar abre em queda com Senado dos EUA e MP de Bolsonaro no radar";
		return awsComprehendService.detectSentiment(text);
	}

}
