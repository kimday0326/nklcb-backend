package com.hwi.core.api.article;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwi.core.api.article.dto.ArticleDetailResponse;
import com.hwi.core.api.article.dto.ArticleSummaryResponse;
import com.hwi.core.api.support.response.ApiResponse;
import com.hwi.storage.db.core.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ArticleService {

	private final ArticleRepository articleRepository;

	public ApiResponse<ArticleSummaryResponse> getArticles() {
		return null;
	}

	public ApiResponse<ArticleDetailResponse> getArticleDetail(Long articleId) {
		return null;
	}

}
