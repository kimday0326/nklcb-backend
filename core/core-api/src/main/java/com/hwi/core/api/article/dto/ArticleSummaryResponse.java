package com.hwi.core.api.article.dto;

import java.time.LocalDateTime;

import com.hwi.storage.db.core.Article;

public record ArticleSummaryResponse(
	Long id,
	String title,
	String summary,
	String author,
	String company,
	String link,
	LocalDateTime publishedAt
) {
	public static ArticleSummaryResponse from(Article article) {
		return new ArticleSummaryResponse(
			article.getId(),
			article.getTitle(),
			article.getSummary(),
			article.getAuthor(),
			article.getCompany().getName(),
			article.getLink(),
			article.getPublishedAt()
		);
	}
}
