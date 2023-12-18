package com.ssafy.pokemon.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String userId;
    private String userPassword;
    private String userNickname;

    @Builder
    public User(String id, String password, String nickname){
        super();
        this.userId = id;
        this.userPassword = password;
        this.userNickname = nickname;
    }

    public User(String id, String password){
        super();
        this.userId = id;
        this.userPassword = password;
    }
}
