package com.mawippel.b3newsapi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "news")
public class NewsEntity extends SentimentScoredEntity {

	@Column
	private String title;

	@Column
	private String href;

	@Column
	private LocalDateTime created_at;

}
