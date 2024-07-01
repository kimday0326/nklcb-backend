package com.hwi.core.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwi.storage.db.core.Article;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

	// 페이징 조회
	@GetMapping
	public Article getArticle() {
		return null;
	}

	// 태그별 조회
	@GetMapping("/tags")
	public Article getArticlesByTag(@RequestParam String tag) {
		return null;
	}

}
