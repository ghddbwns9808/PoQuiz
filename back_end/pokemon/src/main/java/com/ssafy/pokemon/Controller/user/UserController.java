package com.ssafy.pokemon.Controller.user;

import com.ssafy.pokemon.dto.User;
import com.ssafy.pokemon.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@Tag(name = "User", description = "User API")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService us;

    @PostMapping
    @Operation(summary = "유저 등록", description = "id, password, nickname 3가지를 등록한다.")
    public User userInsert(@RequestBody User user){
        return us.join(user);
    }

    @DeleteMapping
    @Operation(summary = "유저 삭제", description = "유저 정보를 완전히 삭제한다.")
    public Boolean userDelect(String id){
        us.userLeave(id);
        return true;
    }

    @PutMapping
    @Operation(summary = "유저 업데이트", description = "유저 DTO에 들어간 정보(user_id)로 유저의 닉네임을 수정한다.")
    public Boolean userNicknameUpdate(@RequestBody User user){
        return us.userUpdate(user.getUserId(), user.getUserNickname());
    }

    @GetMapping
    @Operation(summary = "유저 조회", description = "유저 정보를 반환한다.")
    public User userSelect(String id){
        return us.userSelect(id);
    }

    @GetMapping("/duplicate/id")
    @Operation(summary = "유저 아이디 사용 여부 조회", description = "유저 정보를 조회한다. (이때, 유저 아이디 중복 확인도 가능하다.)")
    public Boolean isUserId(String id){
        return us.isUserIdCheck(id);
    }

    @GetMapping("/duplicate/nickname")
    @Operation(summary = "유저 닉네임 사용 여부 조회", description = "닉네임이 사용되고 있는지 반환한다.")
    public Boolean isUserNickName(String nickname){
        return us.isUserNicknameCheck(nickname);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "입력된 정보를 통해서 유저 id, password를 매칭해서 성공,실패 여부를 반환한다.")
    public User login(@RequestBody User user, HttpServletResponse response) throws UnsupportedEncodingException{
        logger.info("id:{}", user.getUserId());
        User selected = us.login(user.getUserId(), user.getUserPassword());
        if(selected != null){
            Cookie cookie = new Cookie("loginId", URLEncoder.encode(selected.getUserId(), "utf-8"));
            cookie.setMaxAge(1000 * 1000);
            response.addCookie(cookie);
        }
        else{
            return new User("none", "none", "none");
        }
        return selected;
    }
}
