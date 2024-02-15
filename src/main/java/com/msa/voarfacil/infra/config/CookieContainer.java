package com.msa.voarfacil.infra.config;

import java.util.Set;

import com.gargoylesoftware.htmlunit.util.Cookie;

public interface CookieContainer {
    Set<Cookie> getCookies();
    void processCookies(Set<Cookie> cookies);
}
