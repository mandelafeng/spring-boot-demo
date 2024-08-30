package com.cfhui.controller;

import com.cfhui.ChineseSearch;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2024/8/30 上午 10:30
 */
@Controller
public class IndexController {
    @GetMapping("/test")
    public ModelAndView result() throws Exception {
        ModelAndView view = new ModelAndView("index");
        String indexDir = "E:\\lucene2";
        String q = "南京文明";
        List<String> list = ChineseSearch.search(indexDir, q);
        view.addObject("list", list);
        return view;
    }

    @GetMapping("/test2")
    public ModelAndView examplePage() {
        ModelAndView modelAndView = new ModelAndView("example");  // 视图名称必须与文件名一致
        modelAndView.addObject("message", "Hello, World!");
        modelAndView.addObject("items", Arrays.asList("Item 1", "Item 2", "Item 3"));
        return modelAndView;
    }
}

