package com.ssafy.pokemon.Controller.rank;


import com.ssafy.pokemon.dto.Rank;
import com.ssafy.pokemon.service.rank.RankNormalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank-normal")
@CrossOrigin("*")
@Tag(name = "RankNormal", description = "Normal Rank API")
public class RankNormalController {

    @Autowired
    private RankNormalService rns;

    @PostMapping
    @Operation(summary = "점수 등록", description = "유저 점수를 등록합니다.\n유저가 있어야 등록이 가능합니다. (유저 최상위 점수만 등록됩니다.)")
    public Boolean insert(@RequestBody Rank rank){
        return rns.insertScore(rank);
    }

    @GetMapping
    @Operation(summary = "점수 조회", description = "해당 유저의 점수를 조회합니다.")
    public Rank select(String id){
        return rns.getScore(id);
    }

    @PutMapping
    @Operation(summary = "점수 수정", description = "유저의 점수를 수정합니다. 가장 높은 점수만 갱신됩니다.")
    public Boolean update(@RequestBody Rank rank){
        return rns.updateScore(rank);
    }

    @GetMapping("/all")
    @Operation(summary = "전체 점수 조회", description = "모든 유저의 점수를 반환합니다. (상위 10개)")
    public List<Rank> selectAll(){
        return rns.allScoreSelect();
    }

    @DeleteMapping
    @Operation(summary = "점수 삭제", description = "해당 유저의 점수를 삭제합니다. (유저 삭제시 실행되는 기능입니다.)")
    public void delete(String id){rns.deleteScore(id);}
}
