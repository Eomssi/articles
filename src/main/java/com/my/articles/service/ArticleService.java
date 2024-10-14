package com.my.articles.service;

import com.my.articles.dao.ArticleDAO;
import com.my.articles.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleService {
    @Autowired
    EntityManager em;

    // 모든 기사 목록 가져오기
    public List<ArticleDAO> findAllArticles() {
        // JPQL로 Article 엔티티 목록 가져오기
        List<Article> articles = em.createQuery("SELECT a FROM Article a", Article.class).getResultList();

        // Article 엔티티를 ArticleDAO로 변환하여 반환
        return articles.stream()
                .map(article -> new ArticleDAO(article.getId(), article.getTitle(), article.getContent()))
                .collect(Collectors.toList());
    }
}
