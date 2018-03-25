package com.team.mrobot.web.repository;

import com.team.mrobot.web.domain.Catagory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatagoryRepository extends JpaRepository<Catagory,Long> {

    List<Catagory> findAllByLevel(Integer level);

    List<Catagory> findAllBySuperior(Integer superior);
}
