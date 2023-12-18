package com.ssafy.pokemon.service.user;

import com.ssafy.pokemon.dto.Rank;
import com.ssafy.pokemon.mapper.*;
import com.ssafy.pokemon.dto.User;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static UserServiceImpl instance;
    private UserServiceImpl(){}
    public static UserServiceImpl getInstance(){
        if(instance == null){ instance = new UserServiceImpl();}
        return instance;
    }

    @Autowired
    private UserMapper usermapper;

    @Autowired
    private RankLowMapper rlm;

    @Autowired
    private RankNormalMapper rnm;

    @Autowired
    private RankHighMapper rhm;

    @Autowired
    private RankHighestMapper rhm2;

    @Autowired
    private PasswordEncoder pwEncoder;

    @Override
    public User join(User user) {
        user.setUserPassword(pwEncoder.encode(user.getUserPassword()));
        usermapper.userInsert(user);
        return user;
    }

    @Override
    public User login(String id, String pw) {
        User user = usermapper.userSelect(id);
        if(user != null && pwEncoder.matches(pw, user.getUserPassword())){
            return user;
        }else{
            return null;
        }
    }

    @Override
    public Boolean userUpdate(String id, String nickName) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", id);
        map.put("userNickname", nickName);
        try { // 업데이트 성공
            usermapper.userModify(map);

            //점수 (랭킹)에 있는 닉네임도 변경한다.
            rlm.updateUserNickname(map);
            rnm.updateUserNickname(map);
            rhm.updateUserNickname(map);
            rhm2.updateUserNickname(map);

            return true;
        } catch (PersistenceException e) {
            // 업데이트 실패
            return false;
        }
    }

    @Override
    public void userLeave(String id) {
        usermapper.userDelete(id);
    }

    @Override
    public User userSelect(String id) {
        return usermapper.userSelect(id);
    }

    @Override
    public boolean isUserIdCheck(String id) {
        User user = usermapper.userSelect(id);
        if(user != null){
            return true;
        }else {return false;}
    }

    @Override
    public boolean isUserNicknameCheck(String nickName) {
        String nickname = usermapper.userNicknameCheck(nickName);
        if(nickname != null){return true;}
        else return false;
    }
}
