package com.youkill.composeown.service;

import com.youkill.composeown.dao.BlogUserDao;
import com.youkill.composeown.entity.BlogUser;
import com.youkill.composeown.utils.AuthenticationRequest;
import com.youkill.composeown.utils.AuthenticationResponse;
import com.youkill.composeown.utils.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    BlogUserDao blogUserDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    public BlogUser SelectUser(String email,String password){
        BlogUser user=null;
        if (email!=null) {
//            user = blogUserDao.selectNameByUser(email,password);
        }
        return user;
    }
    public AuthenticationResponse register(BlogUser request){
        var jwtToken=jwtService.generateToken(request);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
//        blogUserDao.insert(new BlogUser());
        BlogUser blogUser=blogUserDao.selectNameByUser(request.getEmail());
        if (blogUser==null){
            return null;
        }
        if (passwordEncoder.matches(request.getPassword(),blogUser.getPassword())){
            var jwtToken=jwtService.generateToken(blogUser);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
//
//        repository.save(blogUser);
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var jwtToken=jwtService.generateToken(new BlogUser());
        return null;
    }
    public ResponseResult jwt_check(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        final String userEmail;
        System.out.println(authHeader);
        userEmail = jwtService.extractUsername(authHeader);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(authHeader, userDetails)) {
                return new ResponseResult(200,"check","fail");
            }

        }
        return new ResponseResult(200,"check","success");
    }
}
