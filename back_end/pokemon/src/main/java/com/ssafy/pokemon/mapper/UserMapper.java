package com.ssafy.pokemon.mapper;

import com.ssafy.pokemon.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.Map;

@Mapper
public interface UserMapper {
    /**
     * 사용자 정보를 DB에 저장한다. (Create)
     **/
    public void userInsert(User user);

    /** 사용자 정보를 확인해 로그인 시킨다. (Read)**/
    public User userSelect(String id);

    /** 사용자 정보 중 닉네임 정보를 수정한다. (Update) **/
    public void userModify(Map<String,String> idNickname) throws PersistenceException;

    /** 사용자 정보를 삭제한다. (Delete) **/
    public void userDelete(String id);

    /** 사용자 nickname 사용여부를 반환한다. **/
    public String userNicknameCheck(String nickName);

}
