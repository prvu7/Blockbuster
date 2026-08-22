package com.blockbuster.dto;

public record CrewMemberDto(
        Long id,
        String name,
        String job,
        String department
) {}
