package com.team.mrobot.web.repository;

import com.team.mrobot.web.domain.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface AuthInfoRepository extends JpaRepository<AuthInfo,Long> {

    AuthInfo save(AuthInfo authInfo);


}
