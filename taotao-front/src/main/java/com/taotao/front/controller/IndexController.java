package com.taotao.front.controller;

import com.taotao.front.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

        @Autowired
        private IndexService indexService;

        /**
         * 首页
         * 
         * @return
         */
        @RequestMapping(value = "index", method = RequestMethod.GET)
        public ModelAndView index() {
                ModelAndView mv = new ModelAndView("index");
                // 大广告位数据
                mv.addObject("indexAd1", this.indexService.queryIndexAd1());
                // 右上角小广告
                mv.addObject("indexAd2", this.indexService.queryIndexAd2());
                mv.addObject("indexAd3", this.indexService.queryIndexAd3());
		 //1楼中间位广告
        mv.addObject("indexAd4", this.indexService.queryIndexAd4());

        // 楼层左下角展示
        mv.addObject("indexlU1", this.indexService.queryIndexluzleft1());

        mv.addObject("indexlU2", this.indexService.queryIndexluleft2());

                return mv;
        }
}
