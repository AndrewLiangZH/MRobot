package com.team.mrobot.web.service;

import com.team.mrobot.web.domain.User;

public interface UserService {

    User findByUsername(String username);

    void saveUser(User user);

    void updateType(Integer type,String username);

}
