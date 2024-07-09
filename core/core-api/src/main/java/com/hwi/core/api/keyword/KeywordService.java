package com.hwi.core.api.keyword;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwi.core.api.article.dto.ArticleSummaryResponse;
import com.hwi.core.api.keyword.dto.KeywordCountResponse;
import com.hwi.storage.db.core.repository.ArticleKeywordRepository;
import com.hwi.storage.db.core.repository.KeywordRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class KeywordService {

	private final KeywordRepository keywordRepository;
	private final ArticleKeywordRepository articleKeywordRepository;

	public List<KeywordCountResponse> getTopKeywords() {
		return articleKeywordRepository.findTop100CountKeyword().stream().map(keywordCount -> {
			return new KeywordCountResponse(keywordCount.keyword().getName(), keywordCount.count());
		}).toList();
	}

	public List<ArticleSummaryResponse> getArticlesByKeyword(String keyword) {
		return null;
	}
}
