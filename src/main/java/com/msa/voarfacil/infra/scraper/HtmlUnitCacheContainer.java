package com.msa.voarfacil.infra.scraper;

import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.Cache;
import com.msa.voarfacil.infra.config.CacheContainer;

@Component
public class HtmlUnitCacheContainer implements CacheContainer {
    
    private final Cache cache;
    
    public HtmlUnitCacheContainer(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Cache getCache() {
       return cache;
    }
    
    
}
