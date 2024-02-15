package com.msa.voarfacil.domain.dto;

public record PassengerDto(
        String firstName,
        String lastName,
        String pnr,
        String eticket,
        String buyer) {
}
