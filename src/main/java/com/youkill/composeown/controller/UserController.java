package com.youkill.composeown.controller;


import com.youkill.composeown.entity.BlogUser;
import com.youkill.composeown.service.UserService;
import com.youkill.composeown.utils.AuthenticationRequest;
import com.youkill.composeown.utils.AuthenticationResponse;
import com.youkill.composeown.utils.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(){
        //这个地方是注册，注意修改一下
        return ResponseEntity.ok(userService.register(new BlogUser()));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(userService.authenticate(request));
    }
    @GetMapping("/check")
    public ResponseResult check(HttpServletRequest request){
        return userService.jwt_check(request);
    }
}
