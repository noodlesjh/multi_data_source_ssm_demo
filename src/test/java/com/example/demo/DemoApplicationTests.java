package com.example.demo;

import com.example.demo.entity.web.HouseTypeSimilarityDO;
import com.example.demo.mapper.ue.UeHouseTypeSimilarityMapper;
import com.example.demo.mapper.ue.UeRoomSimilarityMapper;
import com.example.demo.mapper.ue.User1Mapper;
import com.example.demo.mapper.ue.WebUeHouseTypeMappingMapper;
import com.example.demo.mapper.web.*;
import com.example.demo.util.CommUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @Autowired
    private UeHouseTypeSimilarityMapper ueHouseTypeSimilarityMapper;

    @Autowired
    private WebUeHouseTypeMappingMapper webUeHouseTypeMappingMapper;

    @Autowired
    private HouseTypeSimilarityDOMapper houseTypeSimilarityDOMapper;

    @Autowired
    private HouseTypeDOMapper houseTypeDOMapper;

    @Autowired
    private UeRoomSimilarityMapper ueRoomSimilarityMapper;

    @Autowired
    private RoomPointDOMapper roomPointDOMapper;

    @Autowired
    private RoomSimilarityDOMapper roomSimilarityDOMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void t1() {
        List list = user1Mapper.getAll();
        int b = 3;
    }

    @Test
    public void t2() {
        List list = user2Mapper.getAll();
        int b = 3;
    }

    //    HouseType test    start
    @Test
    public void t3() {
        List list = ueHouseTypeSimilarityMapper.listRights();
        int b = 3;
    }

    @Test
    public void t4() {
        List list = webUeHouseTypeMappingMapper.listByUeHouseTypeId(2L);
        int b = 3;

    }

    @Test
    public void t5() {
        List list = roomPointDOMapper.listByUePointId(2L);
        int b = 3;

    }

    @Test
    public void t6() {
//        HouseTypeDO houseTypeDO = houseTypeDOMapper.getOne(1L);
        List<HouseTypeSimilarityDO> houseTypeSimilarityDOS = houseTypeSimilarityDOMapper.listByHouseTypeId(4L, 1L);
//        try {
//            houseTypeSimilarityDOMapper.delete(houseTypeSimilarityDOS.get(0).getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        HouseTypeSimilarityDO similarityDO = new HouseTypeSimilarityDO();
        similarityDO.setHouseTypeId(4L);
        similarityDO.setSimilarHouseTypeId(1L);
        similarityDO.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        similarityDO.setSimilarity("99.093");
        similarityDO.setDecimalSimilarity(CommUtil.string2Decimal("99.093"));
        try {
            houseTypeSimilarityDOMapper.insert(similarityDO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(similarityDO.getId());
        int b = 3;
    }


//    end


}
