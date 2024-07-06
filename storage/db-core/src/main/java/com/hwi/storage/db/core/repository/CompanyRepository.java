package com.hwi.storage.db.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hwi.storage.db.core.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
