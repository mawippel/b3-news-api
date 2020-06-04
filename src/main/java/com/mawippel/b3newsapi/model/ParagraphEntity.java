package com.mawippel.b3newsapi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "paragraphs")
public class ParagraphEntity extends SentimentScoredEntity {

	@Column(length = 2000)
	private String text;

	@ManyToOne
	@JoinColumn(name = "news_id")
	private NewsEntity news;

	@Column
	private LocalDateTime created_at;

}
