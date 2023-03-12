package com.cfhui.service.impl;

import com.cfhui.service.IDemoService;
import org.springframework.stereotype.Service;

/**
 * @ClassName DemoServiceImpl
 * @Description TODO
 * @Author cfhui
 * @Date 2023/3/12 14:47
 */
@Service
public class DemoServiceImpl implements IDemoService {
    @Override
    public String getMsg() {
        return "world";
    }
}
