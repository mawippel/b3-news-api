package com.mawippel.b3newsapi.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name = "website_name")
	private String websiteName;

	@Column(name = "website_photo")
	private String websitePhoto;

	@Column
	private LocalDateTime created_at;

	@OneToMany(mappedBy = "news", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ParagraphEntity> paragraphs;

	@ManyToMany
	@JoinTable(name = "news_stocks", 
				joinColumns = @JoinColumn(name = "news_id"), 
				inverseJoinColumns = @JoinColumn(name = "ticker"))
	Set<StockEntity> stocks;
}
