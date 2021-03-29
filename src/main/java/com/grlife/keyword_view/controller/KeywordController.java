package com.grlife.keyword_view.controller;

import com.grlife.keyword_view.service.KeywordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class KeywordController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @RequestMapping("/create")
    public String create(Model model, HttpServletRequest req) {

        keywordService.create(req);

        return "/signup";

    }

}
