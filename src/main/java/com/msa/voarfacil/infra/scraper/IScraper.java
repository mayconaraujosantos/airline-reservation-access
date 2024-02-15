package com.msa.voarfacil.infra.scraper;

import java.util.Set;

public interface IScraper<T> {
    Set<T> scrapeInformation(String code);
}
