package com.msa.voarfacil.domain.dto;

public record ReservationCompanyDto(
    String tripType, 
    PassengerDto passengerDto
) {

    public static class Builder {
        private String tripType;
        private PassengerDto passengerDto;

        public Builder tripType(String tripType) {
            this.tripType = tripType;
            return this;
        }

        public Builder passengerDto(PassengerDto passengerDto) {
            this.passengerDto = passengerDto;
            return this;
        }

        public ReservationCompanyDto build() {
            return new ReservationCompanyDto(tripType, passengerDto);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
