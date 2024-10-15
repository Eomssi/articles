package com.my.articles.controller;

import com.my.articles.dao.ArticleDAO;
import com.my.articles.dto.ArticleDTO;
import com.my.articles.entity.Article;
import com.my.articles.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("")
    public String showAllArticles(Model model) {
        List<ArticleDTO> dtoList = articleService.getAllArticle();
        model.addAttribute("articles", dtoList);
        return "/articles/show_all";
    }

    @GetMapping("new")
    public String newArticle(Model model) {
        model.addAttribute("dao", new ArticleDAO());
        return "/articles/new";
    }

    @PostMapping("create")
    public String createArticle(ArticleDAO dao, Model model) {
        return "redirect:/articles";
    }

    @GetMapping("{id}")
    public String showOneArticle(@PathVariable("id") Long id, Model model) {
        ArticleDTO articleDTO = articleService.getOneArticle(id);
        model.addAttribute("article", articleDTO);
        return "/articles/show";
    }

    @GetMapping("{id}/update")
    public String viewUpdateArticle() {
        return "/articles/update";
    }

    @PostMapping("update")
    public String updateArticle() {
        return "redirect:articles";
    }

    @GetMapping("{id}/delete")
    public String deleteArticle(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
//        articleService.deleteArticleById(id);
        redirectAttributes.addFlashAttribute("msg", "정상적으로 삭제되었습니다");
        return "redirect:/";
    }
}
