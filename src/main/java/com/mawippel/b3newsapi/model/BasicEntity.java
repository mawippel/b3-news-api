package com.mawippel.b3newsapi.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BasicEntity {

	@Id
	@Column
	private UUID id;

}
