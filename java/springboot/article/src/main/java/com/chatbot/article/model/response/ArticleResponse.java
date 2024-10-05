package com.chatbot.article.model.response;

import com.chatbot.article.model.common.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse extends Article {
    private String id;
}
