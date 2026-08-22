package com.blockbuster.dto;

import java.util.List;

public record CreditsDto(List<CastMemberDto> cast, List<CrewMemberDto> crew) {}
