package com.taotao.search.controller;

import com.taotao.search.bean.TaotaoResult;
import com.taotao.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("search")
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    private static final Integer ROWS = 32;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView searchItem(@RequestParam("q") String keyWords,
            @RequestParam(value = "page", defaultValue = "1") Integer page) {
        try {
            keyWords = new String(keyWords.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("search");
        TaotaoResult taotaoResult = this.searchService.searchItem(keyWords, page, ROWS);
        mv.addObject("itemList", taotaoResult.getData());

        mv.addObject("query", keyWords);
        mv.addObject("page", page);
        int pages = taotaoResult.getTotal() % ROWS == 0 ? taotaoResult.getTotal().intValue() / ROWS
                : taotaoResult.getTotal().intValue() / ROWS + 1;
        mv.addObject("pages", pages);
        return mv;
    }
}
