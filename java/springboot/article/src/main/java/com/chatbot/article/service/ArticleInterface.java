package com.chatbot.article.service;

import com.chatbot.article.model.request.ArticleRequest;
import com.chatbot.article.model.response.ArticleResponse;

import java.util.List;

public interface ArticleInterface {
    ArticleResponse save(ArticleRequest articleRequest);

    ArticleResponse update(String articleId, ArticleRequest articleRequest);

    String delete(String articleId);

    List<ArticleResponse> findArticle(String articleId, boolean visibility);
}
