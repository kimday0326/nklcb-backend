package com.hwi.storage.db.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "company")
public class Company extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "rss_url")
	private String rssUrl;

}
