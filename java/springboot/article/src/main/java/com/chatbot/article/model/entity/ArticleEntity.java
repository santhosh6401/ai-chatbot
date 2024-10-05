package com.chatbot.article.model.entity;

import com.chatbot.article.model.common.Article;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "articles")
public class ArticleEntity extends Article {

    @Id
    private  String id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;


}
