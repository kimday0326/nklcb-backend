package com.hwi.storage.db.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.hwi.storage.db.core")
@EnableJpaRepositories(basePackages = "com.hwi.storage.db.core")
@EnableJpaAuditing
class CoreJpaConfig {

}
