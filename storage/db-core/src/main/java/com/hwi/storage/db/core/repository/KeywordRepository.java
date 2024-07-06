package com.hwi.storage.db.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hwi.storage.db.core.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
