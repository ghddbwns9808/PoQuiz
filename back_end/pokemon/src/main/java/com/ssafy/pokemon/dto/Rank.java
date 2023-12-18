package com.ssafy.pokemon.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rank {
    private String userId;
    private Integer userScore;
    private String userNickname;

    @Builder
    public Rank(String id, Integer score, String nickname){
        this.userId = id;
        this.userScore = score;
        this.userNickname = nickname;
    }
}
