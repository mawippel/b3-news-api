package com.mawippel.b3newsapi.model;

public enum Sentiment {

	POSITIVE, NEGATIVE;

	public static Sentiment getSentimentByIndex(Float positive, Float negative) {
		return positive > negative ? POSITIVE : NEGATIVE;
	}

}
