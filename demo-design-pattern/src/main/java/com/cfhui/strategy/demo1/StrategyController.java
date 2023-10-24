package com.cfhui.strategy.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StrategyController {

    @Autowired
    FactoryStrategy factoryStrategy;

    @RequestMapping("/strategy")
    @ResponseBody
    public String strategy(@RequestParam("key")String key){
        String result;
        try {
            result = factoryStrategy.getStragegy(key).execute();
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }
}
