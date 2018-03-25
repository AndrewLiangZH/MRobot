package com.team.mrobot.web.service;

import com.team.mrobot.web.domain.AuthInfo;
import com.team.mrobot.web.repository.AuthInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthInfoServiceImpl implements AuthInfoService {

    @Autowired
    private AuthInfoRepository authInfoRepository;

    @Override
    public AuthInfo save(AuthInfo authInfo) {
        return authInfoRepository.save(authInfo);
    }
}
