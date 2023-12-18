package com.ssafy.pokemon.service.rank;

import com.ssafy.pokemon.dto.Rank;

import java.util.List;

public interface RankHighestService {
    /** 점수 Insert **/
    public Boolean insertScore(Rank rank);

    /** 점수 조회 (User_id, User_Score) **/
    public Rank getScore(String id);

    /** 점수 업데이트(상위 점수일 때만) **/
    public Boolean updateScore(Rank rank);

    /** 모든 유저의 점수 목록을 가져옵니다. **/
    public List<Rank> allScoreSelect();

    /** 해당 유저의 점수를 삭제한다. **/
    void deleteScore(String id);

    /** 점수 기록 유저 닉네임을 변경합니다. **/
    Boolean updateUserNickname(String id, String nickname);
}
