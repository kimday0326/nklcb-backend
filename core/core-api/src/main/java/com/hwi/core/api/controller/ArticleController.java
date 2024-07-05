package com.hwi.core.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwi.client.grpc.GrpcClientService;
import com.hwi.storage.db.core.Article;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

	private final GrpcClientService grpcClientService;

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

	@GetMapping("test")
	public String test() {
		grpcClientService.crawlArticles("https://d2.naver.com/d2.atom");
		return "testOK";
	}
}
