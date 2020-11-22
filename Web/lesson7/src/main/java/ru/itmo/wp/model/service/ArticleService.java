package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
    public void validateArticle(Article article) throws ValidationException {
        if (article.getTitle().length() < 4) {
            throw new ValidationException("Title can't be shorter than 4 characters");
        }
        if (article.getTitle().length() > 25) {
            throw new ValidationException("Title can't be longer than 25 characters");
        }
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article find(long id) {
        return articleRepository.find(id);
    }

}
