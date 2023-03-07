package com.cfhui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/7 下午 5:46
 */
@RestController
@RequestMapping("/i18n")
public class I18nController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String i18n() {
        return messageSource.getMessage("i18n.user.name", null, LocaleContextHolder.getLocale());
    }
}
