package com.msa.voarfacil.infra.config;

public class Selectors {

    public static final String ETICKET__CARD_BODY= ".eticket__card-body";

    public static final String TRIP = ".eticket__card-body > div:nth-child(1)";    
    public static final String PASSENGER = "div.eticket__stop > div.eticket__stop-header.ng-scope";
    public static final String ONLY_PASSENGER = ".eticket__card-body > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1)";

    public static final String BASE_URL = "https://voarfacil.net/view/eticket/?chave=";

    public static final String ETICKET_CARD_BODY_NG_SCOPE = ".eticket__card";
}
