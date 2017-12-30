package com.example.demo.web;

import com.example.demo.service.SynWebUeSimilarityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jh
 * @date 2017/12/7 16:10
 */
@RestController
public class HouseTypeSimilarityController {

    @Resource(name = "synWebUeSimilarityService")
    private SynWebUeSimilarityService synWebUeSimilarityService;

    @RequestMapping(value = "/syn/startHouseType.do")
    public String startHouseTypeSyn() {
        synWebUeSimilarityService.startSynHouseType();
        return "ok";
    }

    @RequestMapping(value = "/syn/startRoom.do")
    public String startRoomSyn() {
        synWebUeSimilarityService.startSynRoom();
        return "ok";
    }
}
