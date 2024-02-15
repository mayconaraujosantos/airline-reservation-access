package com.msa.voarfacil.infra.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gargoylesoftware.htmlunit.Cache;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.msa.voarfacil.infra.scraper.HtmlUnitCacheContainer;
import com.msa.voarfacil.infra.scraper.HtmlUnitCookieContainer;

@Configuration
public class HtmlUnitConfig {
    @Bean
    public Cache htmlUnitCache() {
        return new Cache();
    }

    @Bean
    public WebClient webClient(Cache htmlUnitCache) {
        WebClient webClient = new WebClient();
        webClient.setCache(htmlUnitCache);
        return webClient;
    }

    @Bean
    public HtmlUnitCacheContainer htmlUnitCacheContainer(Cache htmlUnitCache) {
        return new HtmlUnitCacheContainer(htmlUnitCache);
    }

    @Bean
    public Set<Cookie> cookieSet() {
        return new HashSet<>();
    }

    @Bean
    public HtmlUnitCookieContainer htmlUnitCookieContainer(Set<Cookie> cookieSet) {
        return new HtmlUnitCookieContainer(cookieSet);
    }
}
