package com.mawippel.b3newsapi.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "news")
public class NewsEntity {

	@Id
	@Column
	private UUID id;
	
	@Column
	private String title;
	
	@Column
	private String href;
	
	@Column	
	private LocalDateTime created_at;	

}
