package com.team.mrobot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team.mrobot.web.domain.User;

/**
 * Project: TaaS-test
 * Author: AndrewLiang
 * Date: 2017/9/28
 * Description:
 */

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
