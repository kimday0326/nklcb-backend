package com.hwi.core.api.article.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.hwi.storage.db.core.Article;

public record ArticleDetailResponse(
	Long id,
	String title,
	String summary,
	String content,
	String author,
	String link,
	LocalDateTime publishedAt,
	String company,
	List<String> keywords
) {
	public static ArticleDetailResponse from(Article article, List<String> keywords) {
		return new ArticleDetailResponse(
			article.getId(),
			article.getTitle(),
			article.getSummary(),
			article.getContent(),
			article.getAuthor(),
			article.getLink(),
			article.getPublishedAt(),
			article.getCompany().getName(),
			keywords
		);
	}
}
