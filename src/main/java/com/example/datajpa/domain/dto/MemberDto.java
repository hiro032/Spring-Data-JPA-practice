package com.example.datajpa.domain.dto;

import lombok.Data;

@Data
public class MemberDto {

    private String username;
    private String teamName;

    public MemberDto(String username, String teamName) {
        this.username = username;
        this.teamName = teamName;
    }
}
