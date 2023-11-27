package com.youkill.composeown.common;

import com.youkill.composeown.entity.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BlogUser,Integer> {
    Optional<BlogUser> findByEmail(String emil);

}
