package ru.itmo.wp.web.page;

import com.google.common.util.concurrent.Service;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage {
    private final ArticleService articleService = new ArticleService();
    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void article(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setText(request.getParameter("text"));
        User user = (User) request.getSession().getAttribute("user");

        if(user == null) {
            request.getSession().setAttribute("message", "You are not logged in!");
            throw new RedirectException("/index");
        }
        article.setUserId(user.getId());
        articleService.validateArticle(article);
        articleService.addArticle(article);

        request.getSession().setAttribute("message", "You are successfully added article!");
        throw new RedirectException("/index");
    }

}
