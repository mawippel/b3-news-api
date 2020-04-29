package com.mawippel.b3newsapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "stocks")
public class StockEntity {
	
	@Id
	@Column
	private String ticker;

	@Column
	private String name;
	
	@Column
	private String company;

}
