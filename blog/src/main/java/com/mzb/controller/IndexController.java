package com.mzb.controller;

import com.mzb.service.BlogService;
import com.mzb.service.TypeService;
import com.mzb.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(6));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                     Pageable pageable,@RequestParam String query, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }

}
