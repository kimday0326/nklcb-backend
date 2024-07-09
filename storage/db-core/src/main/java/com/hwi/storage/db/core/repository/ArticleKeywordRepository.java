package com.hwi.storage.db.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hwi.storage.db.core.ArticleKeyword;
import com.hwi.storage.db.core.KeywordCount;

public interface ArticleKeywordRepository extends JpaRepository<ArticleKeyword, Long> {
	@Query("SELECT new com.hwi.storage.db.core.KeywordCount(ak.keyword, COUNT(ak.keyword)) FROM ArticleKeyword ak JOIN ak.keyword k GROUP BY ak.keyword ORDER BY COUNT(ak.keyword) DESC")
	List<KeywordCount> findTop100CountKeyword();
}
