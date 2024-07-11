package com.hwi.core.api.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hwi.client.grpc.GrpcClientService;
import com.hwi.storage.db.core.Article;
import com.hwi.storage.db.core.ArticleKeyword;
import com.hwi.storage.db.core.Company;
import com.hwi.storage.db.core.Keyword;
import com.hwi.storage.db.core.repository.ArticleKeywordRepository;
import com.hwi.storage.db.core.repository.ArticleRepository;
import com.hwi.storage.db.core.repository.CompanyRepository;
import com.hwi.storage.db.core.repository.KeywordRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ArticleScheduler {
	private static final String ZONE = "Asia/Seoul";
	private static long count = 0;

	private final GrpcClientService grpcClientService;
	private final ArticleRepository articleRepository;
	private final CompanyRepository companyRepository;
	private final KeywordRepository keywordRepository;
	private final ArticleKeywordRepository articleKeywordRepository;

	@Async
	@Transactional
	@Scheduled(cron = "* * 3 * * *", zone = ZONE) // 매일 새벽 3시에 업데이트
	public void crawlArticles() {
		// 가장 최신 기사를 데이터베이스에서 가져옵니다.
		final Article latestArticle = articleRepository.findLatestArticle();
		LocalDateTime startDateTime = latestArticle.getPublishedAt(); // 크롤링 시작 시간
		LocalDateTime endDateTime = LocalDateTime.now(); // 크롤링 종료 시간 (현재 시간)

		// 모든 회사 정보를 데이터베이스에서 가져옵니다.
		final List<Company> companies = companyRepository.findAll();

		// 각 회사에 대해 기사 크롤링을 수행합니다.
		for (Company company : companies) {
			log.info(">>>>>> Crawl Articles for Company = {}", company.getName()); // 회사 이름 로그 출력
			grpcClientService.crawlArticles(company.getRssUrl(), startDateTime, endDateTime)
				.forEach(crawledArticle -> {
					try {
						// 중복된 제목의 기사가 있는지 확인합니다.
						if (articleRepository.findByTitle(crawledArticle.getTitle()).isPresent()) {
							throw new DataIntegrityViolationException("Duplicate article found"); // 중복된 기사가 있으면 예외 발생
						}
						// 크롤링된 기사를 데이터베이스에 저장합니다.
						final Article savedArticle = articleRepository.save(Article.builder()
							.title(crawledArticle.getTitle())
							.summary(crawledArticle.getSummary())
							.content(crawledArticle.getContent())
							.author(crawledArticle.getAuthor())
							.link(crawledArticle.getLink())
							.publishedAt(crawledArticle.getPublishedAt())
							.company(company)
							.build());

						// 크롤링된 기사의 키워드를 처리합니다.
						crawledArticle.getKeywords().forEach(keyword -> {
							// 키워드를 데이터베이스에서 찾거나 새로 저장합니다.
							final Keyword savedKeyword = keywordRepository.findByName(keyword)
								.orElseGet(() -> keywordRepository.save(new Keyword(keyword)));
							// 기사와 키워드를 연결하는 관계를 저장합니다.
							articleKeywordRepository.save(new ArticleKeyword(savedArticle, savedKeyword));
						});
					} catch (DataIntegrityViolationException e) {
						// 중복된 기사가 발견된 경우 경고 로그를 출력하고 해당 기사를 건너뜁니다.
						log.warn("Duplicate article found and skipped: {}", crawledArticle.getTitle());
					}
				});
		}
	}

}
