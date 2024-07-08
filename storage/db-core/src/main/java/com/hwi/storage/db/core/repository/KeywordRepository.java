package com.hwi.storage.db.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hwi.storage.db.core.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
	Optional<Keyword> findByName(String keyword);
}
