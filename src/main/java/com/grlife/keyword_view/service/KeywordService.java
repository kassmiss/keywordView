package com.grlife.keyword_view.service;

import com.grlife.keyword_view.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    // νμΌμμ±
    public void create(HttpServletRequest req) {

        keywordRepository.create(req);

    }
}
