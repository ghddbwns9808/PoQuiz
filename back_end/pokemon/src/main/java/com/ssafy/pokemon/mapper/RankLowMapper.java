package com.ssafy.pokemon.mapper;

import com.ssafy.pokemon.dto.Rank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;
import java.util.Map;

@Mapper
public interface RankLowMapper {
    /** 점수 Insert **/
    public void scoreInsert(Rank rank);

    /** 점수 조회 (User_id, User_Score) **/
    public Rank scoreSelect(String id);

    /** 점수 업데이트(상위 점수일 때만) **/
    public void scoreUpdate(Rank rank);

    /** 모든 유저의 점수 목록을 가져옵니다. **/
    public List<Rank> selectAllScore();

    /** 해당 유저의 점수를 삭제한다. **/
    void scoreDelete(String id);

    /** 점수 기록 유저 닉네임을 변경합니다. **/
    Boolean updateUserNickname(Map<String, String> idNickname) throws PersistenceException;
}
