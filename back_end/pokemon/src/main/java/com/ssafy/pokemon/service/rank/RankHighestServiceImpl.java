package com.ssafy.pokemon.service.rank;

import com.ssafy.pokemon.dto.Rank;
import com.ssafy.pokemon.dto.User;
import com.ssafy.pokemon.mapper.RankHighestMapper;
import com.ssafy.pokemon.mapper.UserMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RankHighestServiceImpl implements RankHighestService{

    private RankHighestServiceImpl instance;
    private RankHighestServiceImpl getInstance(){
        if(instance == null){return instance = new RankHighestServiceImpl();}
        return instance;
    }

    @Autowired
    RankHighestMapper rhm;

    @Autowired
    private UserMapper um;

    @Override
    public Boolean insertScore(Rank nowRank) {
        User user = um.userSelect(nowRank.getUserId());

        if(user!=null){
            Rank PreRank = getScore(nowRank.getUserId());

            if(PreRank == null){rhm.scoreInsert(nowRank); return true;}
            else{
                if(PreRank.getUserScore() < nowRank.getUserScore()){
                    try {
                        rhm.scoreUpdate(nowRank);
                        return true;
                    }catch (PersistenceException e){
                        return false;
                    }
                }else{return false;}
            }
        }
        else{
            return false;
        }
    }

    @Override
    public Rank getScore(String id) {
        return rhm.scoreSelect(id);
    }

    @Override
    public Boolean updateScore(Rank nowRank) {
        Rank PreRank = getScore(nowRank.getUserId());
        if(PreRank.getUserScore() < nowRank.getUserScore()){
            try {
                rhm.scoreUpdate(nowRank);
                return true;
            }catch (PersistenceException e){
                return false;
            }
        }else{return false;}
    }

    @Override
    public List<Rank> allScoreSelect() {
        return rhm.selectAllScore();
    }

    @Override
    public void deleteScore(String id){
        rhm.scoreDelete(id);
    }

    @Override
    public Boolean updateUserNickname(String id, String nickName) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", id);
        map.put("userNickname", nickName);

        Rank rank = getScore(id);
        if(rank != null) {
            try {
                rhm.updateUserNickname(map);
                return true;
            } catch (PersistenceException e) {
                return false;
            }
        }else{return false;}
    }
}
