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
		final Article latestArticle = articleRepository.findLatestArticle();
		LocalDateTime startDateTime = latestArticle.getPublishedAt();
		LocalDateTime endDateTime = LocalDateTime.now();
		// LocalDateTime startDateTime = LocalDateTime.now().minusDays(30 * count + 30);
		// LocalDateTime endDateTime = LocalDateTime.now().minusDays(30 * count++);

		final List<Company> companies = companyRepository.findAll();

		for (Company company : companies) {
			log.info(">>>>>> Crawl Articles for Company = {}", company.getName());
			grpcClientService.crawlArticles(company.getRssUrl(), startDateTime, endDateTime)
				.forEach(crawledArticle -> {
					try {
						if (articleRepository.findByTitle(crawledArticle.getTitle()).isPresent()) {
							throw new DataIntegrityViolationException("Duplicate article found");
						}
						final Article savedArticle = articleRepository.save(Article.builder()
							.title(crawledArticle.getTitle())
							.summary(crawledArticle.getSummary())
							.content(crawledArticle.getContent())
							.author(crawledArticle.getAuthor())
							.link(crawledArticle.getLink())
							.publishedAt(crawledArticle.getPublishedAt())
							.company(company)
							.build());

						crawledArticle.getKeywords().forEach(keyword -> {
							final Keyword savedKeyword = keywordRepository.findByName(keyword)
								.orElseGet(() -> keywordRepository.save(new Keyword(keyword)));
							articleKeywordRepository.save(new ArticleKeyword(savedArticle, savedKeyword));
						});
					} catch (DataIntegrityViolationException e) {
						log.warn("Duplicate article found and skipped: {}", crawledArticle.getTitle());
					}
				});
		}
	}
}
