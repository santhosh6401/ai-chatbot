package com.chatbot.article.service.impl;

import com.chatbot.article.model.entity.ArticleEntity;
import com.chatbot.article.model.request.ArticleRequest;
import com.chatbot.article.model.response.ArticleResponse;
import com.chatbot.article.repository.ArticleRepository;
import com.chatbot.article.service.ArticleInterface;
import com.chatbot.article.util.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleImplementation implements ArticleInterface {

    private final ArticleRepository articleRepository;

    private final MongoTemplate template;

    private final Helper helper;

    @Override
    public ArticleResponse save(ArticleRequest articleRequest) {

        ArticleEntity entity = new ArticleEntity();

        BeanUtils.copyProperties(articleRequest, entity);

        entity.setId(helper.generateId("ART"));
        entity.setCreatedTime(LocalDateTime.now());
        entity.setUpdatedTime(LocalDateTime.now());

        articleRepository.save(entity);

        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setId(entity.getId());
        articleResponse.setData(entity.getData());
        articleResponse.setTitle(entity.getTitle());
        articleResponse.setVisibility(entity.isVisibility());

        log.info("record saved successfully ....");

        return articleResponse;

    }

    @Override
    public ArticleResponse update(String articleId, ArticleRequest articleRequest) {

        ArticleEntity entity;

        Optional<ArticleEntity> entityOptional = articleRepository.findById(articleId);

        if (entityOptional.isPresent()) {

            entity = entityOptional.get();

            BeanUtils.copyProperties(articleRequest, entity);

            articleRepository.save(entity);

            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setId(entity.getId());
            articleResponse.setData(entity.getData());
            articleResponse.setTitle(entity.getTitle());
            articleResponse.setVisibility(entity.isVisibility());

            log.info("record saved successfully ....");

            return articleResponse;


        }

        log.info("record not exists ....");

        return null;
    }

    @Override
    public String delete(String articleId) {

        Optional<ArticleEntity> entityOptional = articleRepository.findById(articleId);

        if (entityOptional.isPresent()) {

            articleRepository.deleteById(articleId);

            log.info("deleted successfully ....");

            return "SUCCESS";

        }

        log.info("record not available .... ");

        return "FAILED";
    }

    @Override
    public List<ArticleResponse> findArticle(String articleId, boolean visibility) {

        Query query = new Query();

        if(Objects.nonNull(articleId))
            query.addCriteria(Criteria.where("id").is(articleId));

        query.addCriteria(Criteria.where("visibility").is(visibility));

        List<ArticleEntity> entities = template.find(query , ArticleEntity.class);

        log.info("founded records :: {} " , entities);

        if(entities.isEmpty())
            return new ArrayList<>();

        List<ArticleResponse> responseList = new ArrayList<>();

        for(ArticleEntity entity : entities){
            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setId(entity.getId());
            articleResponse.setData(entity.getData());
            articleResponse.setTitle(entity.getTitle());
            articleResponse.setVisibility(entity.isVisibility());

            responseList.add(articleResponse);
        }

        return responseList;
    }
}
