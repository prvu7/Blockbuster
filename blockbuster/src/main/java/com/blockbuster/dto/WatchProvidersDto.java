package com.blockbuster.dto;

import java.util.Map;

public record WatchProvidersDto(Map<String, CountryProvidersDto> results) {}
