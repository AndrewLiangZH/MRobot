package com.team.mrobot.web.service;

import com.team.mrobot.web.domain.Catagory;

import java.util.List;

public interface CatagoryService {

    List<Catagory> findAllByLevel(Integer level);

    List<Catagory> findAllBySuperior(Integer superior);
}
