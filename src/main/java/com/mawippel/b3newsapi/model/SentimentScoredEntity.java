package com.mawippel.b3newsapi.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class SentimentScoredEntity extends BasicEntity {

	@Column
	private Float positive;

	@Column
	private Float negative;

	@Column
	private Float neutral;

	@Column
	private Float mixed;

	@Enumerated(EnumType.STRING)
	private Sentiment sentiment;

}
