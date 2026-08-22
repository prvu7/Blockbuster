package com.blockbuster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CastMemberDto(
        Long id,
        String name,
        String character,
        @JsonProperty("profile_path") String profilePath
) {}
