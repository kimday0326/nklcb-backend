package com.hwi.client.grpc;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CrawledArticle {
	private String title;
	private String summary;
	private String content;
	private String author;
	private String link;
	private LocalDateTime publishedAt;
	private List<String> tags;

	@Builder
	public CrawledArticle(
		String title,
		String summary,
		String content,
		String author,
		String link,
		LocalDateTime publishedAt) {
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.author = author;
		this.link = link;
		this.publishedAt = publishedAt;
	}
}
