package com.team.mrobot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team.mrobot.web.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Modifying
    @Query("update User set type=?1 where username=?2")
    void updateType(Integer type,String username);

}
