package com.hwi.client.grpc;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import net.devh.boot.grpc.client.inject.GrpcClient;

import io.grpc.StatusRuntimeException;

@Service
public class GrpcClientService {

	private final Logger logger = Logger.getLogger(GrpcClientService.class.getName());

	@GrpcClient("crawling-grpc-server")
	private CrawlingServiceGrpc.CrawlingServiceBlockingStub crawlingStub;

	public CrawledArticle crawlArticles(final String rssLink) {
		try {
			final CrawlingServiceProto.CrawlingResponse response = this.crawlingStub.crawl(
				CrawlingServiceProto.CrawlingRequest.newBuilder()
					.setRssLink(rssLink)
					.build());
			return CrawledArticle.builder()
				.title(response.getTitle())
				.summary(response.getSummary())
				.content(response.getContent())
				.author(response.getAuthor())
				.link(response.getLink())
				.publishedAt(DateTimeUtil.parseToLocalDateTime(response.getPublishedAt()))
				.build();

		} catch (final StatusRuntimeException e) {
			logger.warning("RPC failed: " + e.getStatus());
			return null;
		}
	}
}
