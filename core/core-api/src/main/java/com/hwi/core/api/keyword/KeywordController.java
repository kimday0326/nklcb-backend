package com.hwi.core.api.keyword;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwi.core.api.article.dto.ArticleSummaryResponse;
import com.hwi.core.api.keyword.dto.KeywordCountResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Tag(name = "Keyword", description = "키워드 API")
@RestController
@RequestMapping("/api/v1/keywords")
public class KeywordController {

	private final KeywordService keywordService;

	@Operation(summary = "상위 키워드 조회", description = "상위 50개의 키워드를 조회합니다.")
	@GetMapping
	public List<KeywordCountResponse> getTopKeywords() {
		return keywordService.getTopKeywords();
	}

	@Operation(summary = "키워드별 아티클 조회", description = "특정 키워드에 해당하는 아티클을 조회합니다.")
	@GetMapping("/{keyword}/articles")
	public List<ArticleSummaryResponse> getArticlesByKeyword(@PathVariable String keyword) {
		return keywordService.getArticlesByKeyword(keyword);
	}

}
