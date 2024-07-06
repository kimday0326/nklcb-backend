package com.hwi.storage.db.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hwi.storage.db.core.ArticleKeyword;

public interface ArticleKeywordRepository extends JpaRepository<ArticleKeyword, Long> {
}
