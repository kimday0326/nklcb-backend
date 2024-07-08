package com.hwi.storage.db.core.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hwi.storage.db.core.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	@Query(value = "SELECT a FROM Article a ORDER BY a.publishedAt DESC LIMIT 1")
	Article findLatestArticle();

	Page<Article> findAll(Pageable pageable);

	Optional<Article> findByTitle(String title);
}
