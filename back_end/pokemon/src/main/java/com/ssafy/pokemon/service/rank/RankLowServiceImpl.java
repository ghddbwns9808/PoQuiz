package com.ssafy.pokemon.service.rank;

import com.ssafy.pokemon.dto.Rank;
import com.ssafy.pokemon.dto.User;
import com.ssafy.pokemon.mapper.RankLowMapper;
import com.ssafy.pokemon.mapper.UserMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RankLowServiceImpl implements RankLowService{
    private static final Logger logger = LoggerFactory.getLogger(RankLowServiceImpl.class);
    private static RankLowServiceImpl instance;
    private RankLowServiceImpl(){}
    public static RankLowServiceImpl getInstance(){
        if(instance == null){instance = new RankLowServiceImpl();}
        return instance;
    }

    @Autowired
    private RankLowMapper ranklowmapper;

    @Autowired
    private UserMapper um;

    @Override
    public Boolean insertScore(Rank nowRank) {
        User user = um.userSelect(nowRank.getUserId());

        if(user!=null){
            Rank PreRank = getScore(nowRank.getUserId());

            if(PreRank == null){ranklowmapper.scoreInsert(nowRank); return true;}
            else{
                if(PreRank.getUserScore() < nowRank.getUserScore()){
                    try {
                        ranklowmapper.scoreUpdate(nowRank);
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
        return ranklowmapper.scoreSelect(id);
    }

    @Override
    public Boolean updateScore(Rank nowRank) {
        Rank PreRank = getScore(nowRank.getUserId());
        if(PreRank.getUserScore() < nowRank.getUserScore()){
            try {
                ranklowmapper.scoreUpdate(nowRank);
                return true;
            }catch (PersistenceException e){
                return false;
            }
        }else{return false;}
    }

    @Override
    public List<Rank> allScoreSelect() {
        return ranklowmapper.selectAllScore();
    }

    @Override
    public void deleteScore(String id) {
        ranklowmapper.scoreDelete(id);
    }

    @Override
    public Boolean updateUserNickname(String id, String nickName) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", id);
        map.put("userNickname", nickName);

        Rank rank = getScore(id);
        if(rank != null) {
            try {
                ranklowmapper.updateUserNickname(map);
                return true;
            } catch (PersistenceException e) {
                return false;
            }
        }else{return false;}
    }
}
