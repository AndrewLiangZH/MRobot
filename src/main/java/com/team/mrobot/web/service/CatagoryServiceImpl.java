package com.team.mrobot.web.service;

import com.team.mrobot.web.domain.Catagory;
import com.team.mrobot.web.repository.CatagoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatagoryServiceImpl implements CatagoryService {

    @Autowired
    private CatagoryRepository catagoryRepository;

    @Override
    public List<Catagory> findAllByLevel(Integer level) {
        return catagoryRepository.findAllByLevel(level);
    }

    @Override
    public List<Catagory> findAllBySuperior(Integer superior) {
        return catagoryRepository.findAllBySuperior(superior);
    }
}
