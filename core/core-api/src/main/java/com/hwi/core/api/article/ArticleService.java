package com.hwi.core.api.article;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwi.core.api.article.dto.ArticleDetailResponse;
import com.hwi.core.api.article.dto.ArticleSummaryResponse;
import com.hwi.storage.db.core.Article;
import com.hwi.storage.db.core.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ArticleService {

	private final ArticleRepository articleRepository;

	public List<ArticleSummaryResponse> getArticles(Long page, Long size) {
		PageRequest pageRequest = PageRequest.of(page.intValue(), size.intValue(),
			Sort.by(Sort.Direction.DESC, "publishedAt"));
		return articleRepository.findAll(pageRequest).stream()
			.map(ArticleSummaryResponse::from).toList();
	}

	public ArticleDetailResponse getArticleDetail(Long articleId) {
		final Article article = articleRepository.findById(articleId)
			.orElseThrow(() -> new IllegalArgumentException("해당 아티클이 존재하지 않습니다."));
		final List<String> keywords = article.getArticleKeywords()
			.stream()
			.map(articleKeyword -> articleKeyword.getKeyword().getName())
			.toList();
		return ArticleDetailResponse.from(article, keywords);
	}

	public List<ArticleSummaryResponse> getArticlesByIds(List<Long> ids) {
		return articleRepository.findAllById(ids).stream()
			.map(ArticleSummaryResponse::from)
			.toList();
	}
}
