package com.mawippel.b3newsapi.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class SentimentScoredEntity {

	@Column
	private double positive;

	@Column
	private double negative;

	@Column
	private double neutral;

	@Column
	private double mixed;

}
