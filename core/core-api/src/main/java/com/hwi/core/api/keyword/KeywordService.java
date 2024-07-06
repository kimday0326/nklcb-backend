package com.hwi.core.api.keyword;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwi.core.api.article.dto.ArticleSummaryResponse;
import com.hwi.core.api.keyword.dto.KeywordResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class KeywordService {
	public List<KeywordResponse> getAllKeywords() {
		return null;
	}

	public List<ArticleSummaryResponse> getArticlesByKeyword(String keyword) {
		return null;
	}
}
