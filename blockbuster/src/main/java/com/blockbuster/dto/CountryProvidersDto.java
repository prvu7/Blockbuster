package com.blockbuster.dto;

import java.util.List;

public record CountryProvidersDto(
        String link,
        List<ProviderDto> flatrate,
        List<ProviderDto> rent,
        List<ProviderDto> buy
) {}
