package com.msa.voarfacil.usecase;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;

import com.gargoylesoftware.htmlunit.Cache;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.msa.voarfacil.infra.config.CacheContainer;
import com.msa.voarfacil.infra.config.CookieContainer;
import com.msa.voarfacil.infra.config.Selectors;
import com.msa.voarfacil.infra.scraper.IScraper;

public abstract class ScrapeUseCaseBase<T> implements IScraper<T> {

	private final CacheContainer cacheContainer;
	private final CookieContainer cookieContainer;

	public ScrapeUseCaseBase(CacheContainer cacheContainer, CookieContainer cookieContainer) {
		this.cacheContainer = cacheContainer;
		this.cookieContainer = cookieContainer;
	}

	protected abstract String getSelector();

	protected abstract T extractInformation(DomNode selection);

	protected abstract String extractTextContent(DomNode section, String selector);

	protected abstract String getAlternateSelector();

	protected abstract Logger getLogger();

	@Override
	public Set<T> scrapeInformation(String code) {
		return execute(code, getSelector(), this::extractInformation);
	}

	private <R> Set<R> execute(String code, String selector, Function<DomNode, R> extractionFunction) {
		Set<R> resultSet = new HashSet<>();
		try (WebClient webClient = new WebClient()) {
			getRequestCookies(webClient);

			getCollectorSelectors(code, extractionFunction, resultSet, webClient);

		} catch (FailingHttpStatusCodeException e) {
			getLogger().error("Error: HTTP status code {} - {}", e.getStatusCode(), e.getMessage());
		} catch (MalformedURLException e) {
			getLogger().error("Error: Malformed URL - {}", e.getMessage());
		} catch (IOException e) {
			getLogger().error("Error: I/O Exception - {}", e.getMessage());		
		}
		return resultSet;
	}

	private <R> void getCollectorSelectors(String code, Function<DomNode, R> extractionFunction, Set<R> resultSet,
			WebClient webClient) throws IOException, MalformedURLException {
				Logger logger = getLogger();
		HtmlPage page = webClient.getPage(Selectors.BASE_URL + code);

		webClient.waitForBackgroundJavaScript(5000);

		Set<Cookie> responseCookies = webClient.getCookieManager().getCookies();
		cookieContainer.processCookies(responseCookies);

		final DomNodeList<DomNode> cardSections = page.querySelectorAll(Selectors.ETICKET_CARD_BODY_NG_SCOPE);
		logger.info("cardSections size: {}", cardSections.size());

		for (DomNode cardSection : cardSections) {
		    proccessDivSeletors(cardSection, extractionFunction, resultSet);
		}
	}

	private void getRequestCookies(WebClient webClient) {
		Cache cache = cacheContainer.getCache();
		cache.clear();

		Set<Cookie> requestCookies = cookieContainer.getCookies();
		if (requestCookies != null) {
			addCookiesToWebClient(webClient, requestCookies);
		}
	}


	private <R> void proccessDivSeletors(DomNode cardSection, Function<DomNode, R> extractionFunction, Set<R> resultList) {
		String tripType = cardSection.querySelector(Selectors.TRIP).getTextContent();
		getLogger().info("Trips: {}", tripType);


		String selector = isRoundTrip(tripType) ? getSelector() : getAlternateSelector();
		processSectionsAndAddToResultList(cardSection.querySelectorAll(selector), extractionFunction, resultList);

	}

	private <R> void processSectionsAndAddToResultList(DomNodeList<DomNode> sections, Function<DomNode, R> extractionFunction,
													   Set<R> resultList) {
		getLogger().info("Extracting information");

		if (sections != null && !sections.isEmpty()) {
			for (DomNode section : sections) {
				R result = extractionFunction.apply(section);
				resultList.add(result);
			}
		} else {
			getLogger().warn("No elements found for sections");
		}
	}
		
	

	private boolean isRoundTrip(String tripType) {
        return "IDA".equals(tripType) || "VOLTA".equals(tripType);
    }

	private void addCookiesToWebClient(WebClient webClient, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) { 
			webClient.getCookieManager().addCookie(cookie);
		}
	}
}
