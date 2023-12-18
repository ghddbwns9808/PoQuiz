package com.ssafy.pokemon.service.user;

import com.ssafy.pokemon.dto.User;

public interface UserService {
    /** 사용자 정보를 DB에 저장한다. (Create) **/
    public User join(User user);

    /** 사용자 정보를 확인해 로그인 시킨다. (Read)**/
    public User login(String id, String pw);

    /** 사용자 정보 중 닉네임 정보를 수정한다. (Update) **/
    public Boolean userUpdate(String id, String nickName);

    /** 사용자 정보를 삭제한다. (Delete) **/
    public void userLeave(String id);

    /** 사용자 정보를 반환한다. (Read) **/
    public User userSelect(String id);

    /** 사용자 id 사용 여부를 반환한다. **/
    public boolean isUserIdCheck(String id);

    /** 사용자 nickname 사용여부를 반환한다. **/
    public boolean isUserNicknameCheck(String nickName);

}
