package com.hwi.storage.db.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hwi.storage.db.core.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
