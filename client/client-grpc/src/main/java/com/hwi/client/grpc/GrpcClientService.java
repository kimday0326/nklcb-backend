package com.hwi.client.grpc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import net.devh.boot.grpc.client.inject.GrpcClient;

import io.grpc.StatusRuntimeException;

@Service
public class GrpcClientService {

	private final Logger logger = Logger.getLogger(GrpcClientService.class.getName());

	@GrpcClient("crawling-grpc-server")
	private CrawlingServiceGrpc.CrawlingServiceBlockingStub crawlingStub;

	public List<CrawledArticle> crawlArticles(
		final String rssUrl,
		final LocalDateTime startDateTime,
		final LocalDateTime endDateTime) {
		try {
			final CrawlingServiceProto.CrawlingResponse response = crawlingStub.crawl(
				CrawlingServiceProto.CrawlingRequest.newBuilder()
					.setRssUrl(rssUrl)
					.setStartDateTime(DateTimeUtil.formatLocalDateTime(startDateTime))
					.setEndDateTime(DateTimeUtil.formatLocalDateTime(endDateTime))
					.build());
			return response.getArticlesList().stream()
				.map(article -> CrawledArticle.builder()
					.title(article.getTitle())
					.summary(article.getSummary())
					.content(article.getContent())
					.author(article.getAuthor())
					.link(article.getLink())
					.publishedAt(LocalDateTime.parse(article.getPublishedAt()))
					.keywords(article.getKeywordsList())
					.build()).toList();

		} catch (final StatusRuntimeException e) {
			logger.warning("RPC failed: " + e.getStatus());
			return Collections.emptyList();
		}
	}
}
