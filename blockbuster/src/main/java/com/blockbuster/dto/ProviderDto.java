package com.blockbuster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProviderDto(
        @JsonProperty("provider_id") Long providerId,
        @JsonProperty("provider_name") String providerName,
        @JsonProperty("logo_path") String logoPath
) {}
