package com.my.articles.dao;

import com.my.articles.entity.Article;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class ArticleDAO {
    @Autowired
    EntityManager em;

    // 모든 기사 목록 가져오기
    public List<Article> getAllArticle() {
        String sql = "SELECT a FROM Article a ORDER BY a.id DESC";
        return em.createQuery(sql).getResultList();
    }

    public Article getOneArticle(Long id) {
        return em.find(Article.class, id);
    }

}
