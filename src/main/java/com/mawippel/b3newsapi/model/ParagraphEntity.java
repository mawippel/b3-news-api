package com.mawippel.b3newsapi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "paragraphs")
public class ParagraphEntity extends SentimentScoredEntity {

	@Column
	private String text;

	@OneToOne
	@JoinColumn(name = "news_id")
	private NewsEntity news;

	@Column
	private LocalDateTime created_at;

}
