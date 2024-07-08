package com.hwi.storage.db.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "keyword")
public class Keyword extends BaseEntity {
	@Column(name = "name")
	private String name;

	public Keyword(String name) {
		this.name = name;
	}
}
