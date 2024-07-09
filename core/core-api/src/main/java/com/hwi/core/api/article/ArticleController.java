package com.hwi.core.api.article;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwi.core.api.article.dto.ArticleDetailResponse;
import com.hwi.core.api.article.dto.ArticleSummaryResponse;
import com.hwi.core.api.support.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Tag(name = "Article", description = "아티클 API")
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

	private final ArticleService articleService;

	@GetMapping
	@Operation(summary = "아티클 목록 조회", description = "아티클 목록을 조회합니다.")
	public ApiResponse<List<ArticleSummaryResponse>> getArticles(
		@RequestParam Long page,
		@RequestParam Long size) {
		return ApiResponse.success(articleService.getArticles(page, size));
	}

	@GetMapping("/{articleId}")
	@Operation(summary = "아티클 상세 조회", description = "특정 아티클의 상세 정보를 조회합니다.")
	public ApiResponse<ArticleDetailResponse> getArticleDetail(@PathVariable Long articleId) {
		return ApiResponse.success(articleService.getArticleDetail(articleId));
	}

	@GetMapping("/summaries")
	@Operation(summary = "특정 아티클 요약 조회", description = "특정 아티클들의 요약 정보를 조회합니다.")
	public ApiResponse<List<ArticleSummaryResponse>> getArticlesByIds(
		@RequestParam List<Long> ids) {
		return ApiResponse.success(articleService.getArticlesByIds(ids));
	}
}
