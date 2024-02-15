package com.msa.voarfacil.infra.scraper;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.util.Cookie;
import com.msa.voarfacil.infra.config.CookieContainer;

@Component
public class HtmlUnitCookieContainer implements CookieContainer {
    
    private final Set<Cookie> cookies;
    
    public HtmlUnitCookieContainer(Set<Cookie> cookies) {
        this.cookies = cookies;
    }

    @Override
    public Set<Cookie> getCookies() {
        return cookies;
    }

    @Override
    public void processCookies(Set<Cookie> cookies) {
        this.cookies.addAll(cookies);
    }
}
