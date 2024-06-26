package com.cfhui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class I18nConfig implements WebMvcConfigurer {

    //两种方式区一中即可
    @Bean
    public LocaleResolver localeResolver() {
        //(1)Cookie方式
//        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//        localeResolver.setCookieName("localeCookie");
//        //设置默认区域
//        localeResolver.setDefaultLocale(Locale.CHINA);
//        localeResolver.setCookieMaxAge(3600);//设置cookie有效期.
//        return localeResolver;

        //(2)Session方式
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 默认语言
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 参数名实现国际化效果
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
