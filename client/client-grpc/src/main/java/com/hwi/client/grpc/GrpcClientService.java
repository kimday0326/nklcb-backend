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

	@GrpcClient("crawling-grpc-server") // gRPC 클라이언트 주입
	private CrawlingServiceGrpc.CrawlingServiceBlockingStub crawlingStub;

	public List<CrawledArticle> crawlArticles(
		final String rssUrl,
		final LocalDateTime startDateTime,
		final LocalDateTime endDateTime) {
		try {
			// gRPC 서버로부터 크롤링 결과를 가져오기 위한 요청 생성 및 호출
			final CrawlingServiceProto.CrawlingResponse response = crawlingStub.crawl(
				CrawlingServiceProto.CrawlingRequest.newBuilder()
					.setRssUrl(rssUrl) // RSS URL 설정
					.setStartDateTime(DateTimeUtil.formatLocalDateTime(startDateTime)) // 시작 날짜 및 시간 설정
					.setEndDateTime(DateTimeUtil.formatLocalDateTime(endDateTime)) // 종료 날짜 및 시간 설정
					.build());
			// 응답으로 받은 기사 목록을 CrawledArticle 객체 리스트로 변환하여 반환
			return response.getArticlesList().stream()
				.map(article -> CrawledArticle.builder()
					.title(article.getTitle()) // 기사 제목 설정
					.summary(article.getSummary()) // 기사 요약 설정
					.content(article.getContent()) // 기사 내용 설정
					.author(article.getAuthor()) // 기사 작성자 설정
					.link(article.getLink()) // 기사 링크 설정
					.publishedAt(LocalDateTime.parse(article.getPublishedAt())) // 기사 발행일 설정
					.keywords(article.getKeywordsList()) // 기사 키워드 설정
					.build()).toList();

		} catch (final StatusRuntimeException e) {
			// 예외 발생 시 경고 로그를 남기고 빈 리스트 반환
			logger.warning("RPC failed: " + e.getStatus());
			return Collections.emptyList();
		}
	}
}
