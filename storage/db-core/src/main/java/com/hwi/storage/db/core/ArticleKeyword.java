package com.hwi.storage.db.core;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "article_keyword")
public class ArticleKeyword extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Article article;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "keyword_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Keyword keyword;

	public ArticleKeyword(Article article, Keyword keyword) {
		this.article = article;
		this.keyword = keyword;
	}
}
