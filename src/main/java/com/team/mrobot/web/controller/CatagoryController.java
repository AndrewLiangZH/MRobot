package com.team.mrobot.web.controller;

import com.team.mrobot.web.domain.Catagory;
import com.team.mrobot.web.service.CatagoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CatagoryController {

    @Autowired
    private CatagoryService catagoryService;

    @PostMapping("/catagory")
    public List<Catagory> listCatagory(
            @RequestParam(value = "level", required = false) Integer level,
            @RequestParam(value = "superior", required = false) Integer superior) {
        List<Catagory> catagoryList = new ArrayList<>();
      if (level!=null){
          catagoryList =catagoryService.findAllByLevel(level);
      }
      if (superior!=null){
          catagoryList=catagoryService.findAllBySuperior(superior);
      }

        return catagoryList;
    }


}
