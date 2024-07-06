package com.hwi.core.api.keyword;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwi.core.api.article.dto.ArticleSummaryResponse;
import com.hwi.core.api.keyword.dto.KeywordResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/keywords")
public class KeywordController {

	private final KeywordService keywordService;

	@GetMapping
	public List<KeywordResponse> getAllKeywords() {
		return keywordService.getAllKeywords();
	}

	@GetMapping("/{keyword}/articles")
	public List<ArticleSummaryResponse> getArticlesByKeyword(@PathVariable String keyword) {
		return keywordService.getArticlesByKeyword(keyword);
	}

}
