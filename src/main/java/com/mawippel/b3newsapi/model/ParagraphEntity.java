package com.mawippel.b3newsapi.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "paragraphs")
public class ParagraphEntity {

	@Id
	@Column
	private UUID id;

	@Column
	private String text;

	@OneToOne
	@JoinColumn(name = "news_id")
	private NewsEntity news;

	@Column
	private LocalDateTime created_at;

}
