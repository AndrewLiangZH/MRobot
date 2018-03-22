package com.team.mrobot.web.service;

import com.team.mrobot.web.domain.User;

/**
 * Project: TaaS-test
 * Author: AndrewLiang
 * Date: 2017/9/28
 * Description:
 */
public interface UserService {

    User findByUsername(String username);

    void saveUser(User user);

}
