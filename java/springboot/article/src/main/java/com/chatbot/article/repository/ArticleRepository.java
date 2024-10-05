package com.chatbot.article.repository;

import com.chatbot.article.model.entity.ArticleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<ArticleEntity ,String> {
}
