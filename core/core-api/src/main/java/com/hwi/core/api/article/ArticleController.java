package com.hwi.core.api.article;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwi.core.api.article.dto.ArticleDetailResponse;
import com.hwi.core.api.article.dto.ArticleSummaryResponse;
import com.hwi.core.api.support.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

	private final ArticleService articleService;

	@GetMapping
	public ApiResponse<ArticleSummaryResponse> getArticles() {
		return articleService.getArticles();
	}

	@GetMapping("/{articleId}")
	public ApiResponse<ArticleDetailResponse> getArticleDetail(@PathVariable Long articleId) {
		return articleService.getArticleDetail(articleId);
	}

}
