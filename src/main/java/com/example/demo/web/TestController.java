//package com.example.demo.web;
//
//import com.example.demo.entity.ue.UeHouseTypeSimilarity;
//import com.example.demo.entity.web.HouseTypeSimilarityDO;
//import com.example.demo.mapper.ue.UeHouseTypeSimilarityMapper;
//import com.example.demo.mapper.web.HouseTypeSimilarityDOMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.sql.Timestamp;
//import java.util.List;
//
///**
// * @author jh
// * @date 2017/12/11 17:46
// */
//public class TestController {
//
//
//    @Autowired
//    private UeHouseTypeSimilarityMapper ueHouseTypeSimilarityMapper;
//
//    @Autowired
//    private HouseTypeSimilarityDOMapper houseTypeSimilarityDOMapper;
//
//    @RequestMapping("/getSimilarities")
//    public List<UeHouseTypeSimilarity> getSimilarity() {
//        return ueHouseTypeSimilarityMapper.getAll();
//    }
//
//    @RequestMapping(value = "/updateSimilarity")
//    public void update() {
//        UeHouseTypeSimilarity ueHouseTypeSimilarity = ueHouseTypeSimilarityMapper.getOne(1L);
//        if (ueHouseTypeSimilarity != null) {
//            ueHouseTypeSimilarity.setGmtUpdate(new Timestamp(System.currentTimeMillis()));
//            ueHouseTypeSimilarity.setUpdateStatus((byte) 1);
//            ueHouseTypeSimilarity.setOperator(ueHouseTypeSimilarity.getOperator() + "-jh");
//            try {
//                ueHouseTypeSimilarityMapper.update(ueHouseTypeSimilarity);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        int b = 3;
//    }
//
//    @RequestMapping(value = "/removeUeHouseType")
//    public Boolean removeUeHouseType(Long id) {
//        id = 1L;
//        Boolean f = false;
//        try {
//            ueHouseTypeSimilarityMapper.delete(id);
//            f = true;
//        } catch (Exception e) {
//            f = false;
//            e.printStackTrace();
//        }
//        return f;
//    }
//
//    @GetMapping(value = "/getHouseTypeSDOs")
//    public List<HouseTypeSimilarityDO> getHouseTypeSDOs() {
//        return houseTypeSimilarityDOMapper.getAll();
//    }
//}
