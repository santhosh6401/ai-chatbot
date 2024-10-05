package com.chatbot.article.controller;


import com.chatbot.article.model.request.ArticleRequest;
import com.chatbot.article.model.response.ArticleResponse;
import com.chatbot.article.service.ArticleInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {


   private final  ArticleInterface articleInterface;

    @PostMapping("/create")
    public ArticleResponse create(@RequestBody ArticleRequest articleRequest){
        return articleInterface.save(articleRequest);
    }

    @PutMapping("/edit/{articleId}")
    public ArticleResponse update(@PathVariable("articleId") String articleId,
                         @RequestBody ArticleRequest articleRequest) {
        return articleInterface.update(articleId,articleRequest);
    }

    @DeleteMapping("/delete/{articleId}")
    public String delete(@PathVariable("articleId") String articleId) {
        return articleInterface.delete(articleId);
    }

    @GetMapping("/find")
    public List<ArticleResponse> findArticle(@RequestParam("articleId")String articleId ,
                                             @RequestParam("visibility") boolean visibility) {
        return articleInterface.findArticle(articleId , visibility);
    }
}
