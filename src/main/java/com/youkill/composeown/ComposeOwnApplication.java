package com.youkill.composeown;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("com.youkill.composeown.dao")
@CrossOrigin("**")
public class ComposeOwnApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComposeOwnApplication.class, args);
    }

}
