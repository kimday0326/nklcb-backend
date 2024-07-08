package com.hwi.storage.db.core;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "article")
public class Article extends BaseEntity {

	@Column(name = "title", unique = true)
	private String title;

	@Column(name = "summary")
	private String summary;

	@Column(name = "content", columnDefinition = "TEXT")
	private String content;

	@Column(name = "author")
	private String author;

	@Column(name = "link")
	private String link;

	@Column(name = "published_at")
	private LocalDateTime publishedAt;

	@Column(name = "is_notified")
	private boolean isNotified;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Company company;

	@OneToMany(mappedBy = "article")
	private List<ArticleKeyword> articleKeywords;

	@Builder
	public Article(
		final String title,
		final String summary,
		final String content,
		final String author,
		final String link,
		final LocalDateTime publishedAt,
		final Company company) {
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.author = author;
		this.link = link;
		this.publishedAt = publishedAt;
		this.company = company;
	}
}
