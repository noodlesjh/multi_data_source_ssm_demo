package com.example.demo.service.impl;

import com.example.demo.entity.ue.UeHouseTypeSimilarity;
import com.example.demo.entity.ue.UeRoomSimilarity;
import com.example.demo.entity.ue.WebUeHouseTypeMapping;
import com.example.demo.entity.web.HouseTypeDO;
import com.example.demo.entity.web.HouseTypeSimilarityDO;
import com.example.demo.entity.web.RoomPointDO;
import com.example.demo.entity.web.RoomSimilarityDO;
import com.example.demo.mapper.ue.UeHouseTypeSimilarityMapper;
import com.example.demo.mapper.ue.UeRoomSimilarityMapper;
import com.example.demo.mapper.ue.WebUeHouseTypeMappingMapper;
import com.example.demo.mapper.web.HouseTypeDOMapper;
import com.example.demo.mapper.web.HouseTypeSimilarityDOMapper;
import com.example.demo.mapper.web.RoomPointDOMapper;
import com.example.demo.mapper.web.RoomSimilarityDOMapper;
import com.example.demo.service.SynWebUeSimilarityService;
import com.example.demo.util.CommUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author jh
 * @date 2017/12/7 18:46
 */
@Service(value = "synWebUeSimilarityService")
public class SynWebUeSimilarityServiceImpl implements SynWebUeSimilarityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Value("${operatorName}")
    private String operatorName = "_web";

    @Value("${maxSynErrorNum}")
    private Integer maxSynErrorNum = 200;

    /**
     * 同步户型数据
     */
    @Override
    public void startSynHouseType() {
        logger.error("更新户型相似度--------------------start-------------------------");
        operatorName.intern();

        int errorNum = 0;
        int successNum = 0;
        int i = 1;
        List<UeHouseTypeSimilarity> list;
        List<WebUeHouseTypeMapping> listMapping1;
        List<WebUeHouseTypeMapping> listMapping2;
        WebUeHouseTypeMapping mapping1;
        WebUeHouseTypeMapping mapping2;

        Long webHouseTypeId1;
        Long webHouseTypeId2;

        HouseTypeDO houseTypeDO1;
        HouseTypeDO houseTypeDO2;
        do {
            //1 读相似度 20条
            PageHelper.startPage(i, 20);
            list = ueHouseTypeSimilarityMapper.listRights();
            if (list != null && list.size() != 0) {
                //2 查询WebUeHouseTypeMapping
                for (UeHouseTypeSimilarity ue : list) {
                    if (CommUtil.isEmptyString(ue.getSimilarity()) || CommUtil.string2Decimal(ue.getSimilarity()) == null) {
                        logger.error("ue:相似度有误_similarity" + ue.getSimilarity());
                        errorNum++;
                        continue;
                    }
                    listMapping1 = webUeHouseTypeMappingMapper.listByUeHouseTypeId(ue.getUeHouseTypeId());
                    listMapping2 = webUeHouseTypeMappingMapper.listByUeHouseTypeId(ue.getUeSimilarHouseTypeId());
                    if (listMapping1.isEmpty() || listMapping2.isEmpty()) {
                        logger.error("web:不存在此条数据_ueHouseTypeId:" + ue.getUeHouseTypeId() + "_ueSimilarHouseTypeId:" + ue.getUeSimilarHouseTypeId());
                        errorNum++;
                        continue;
                    }

                    mapping1 = listMapping1.get(0);
                    mapping2 = listMapping2.get(0);

                    webHouseTypeId1 = mapping1.getWebHouseTypeId();
                    webHouseTypeId2 = mapping2.getWebHouseTypeId();
                    if (CommUtil.null2Long(webHouseTypeId1) > 0 && CommUtil.null2Long(webHouseTypeId2) > 0) {
                        //查询web端是否存在
                        houseTypeDO1 = houseTypeDOMapper.getOne(CommUtil.null2Long(webHouseTypeId1));
                        houseTypeDO2 = houseTypeDOMapper.getOne(CommUtil.null2Long(webHouseTypeId2));
                        if (houseTypeDO1 != null && houseTypeDO2 != null) {
                            //3 写入开始 HouseTypeSimilarityDO
                            Boolean flag = false;
                            List<HouseTypeSimilarityDO> houseTypeSimilarityDOS = houseTypeSimilarityDOMapper.listByHouseTypeId(webHouseTypeId1, webHouseTypeId2);
                            if (houseTypeSimilarityDOS.isEmpty()) {
                                //添加
                                HouseTypeSimilarityDO similarityDO = new HouseTypeSimilarityDO();
                                similarityDO.setHouseTypeId(webHouseTypeId1);
                                similarityDO.setSimilarHouseTypeId(webHouseTypeId2);
                                similarityDO.setGmtCreate(new Timestamp(System.currentTimeMillis()));
                                similarityDO.setSimilarity(ue.getSimilarity().trim());
                                similarityDO.setDecimalSimilarity(CommUtil.string2Decimal(ue.getSimilarity()));
                                try {
                                    houseTypeSimilarityDOMapper.insert(similarityDO);
                                    flag = true;
                                } catch (Exception e) {
                                    errorNum++;
                                    logger.error("web:写入错误_HouseTypeId:" + webHouseTypeId1 + "_SimilarHouseTypeId:" + webHouseTypeId2);
                                }
                            } else {
                                //更新
                                if (houseTypeSimilarityDOS.size() > 1) {
                                    int j = 0;
                                    Long similarityId = 0L;
                                    try {
                                        for (HouseTypeSimilarityDO similarityDO : houseTypeSimilarityDOS) {
                                            //第一条不处理
                                            if (j != 0) {
                                                similarityId = similarityDO.getId();
                                                houseTypeSimilarityDOMapper.delete(similarityDO.getId());
                                            }
                                            j++;
                                        }
                                    } catch (Exception e) {
                                        errorNum++;
                                        logger.error("web:删除相似度错误_HouseTypeSimilarityDOId:" + similarityId);
                                        //删除不了说明有关联，对此条数据不处理
                                        continue;
                                    }
                                }
                                HouseTypeSimilarityDO similarityDO = houseTypeSimilarityDOS.get(0);
                                similarityDO.setSimilarity(ue.getSimilarity().trim());
                                similarityDO.setDecimalSimilarity(CommUtil.string2Decimal(ue.getSimilarity().trim()));
                                similarityDO.setGmtModified(new Timestamp(System.currentTimeMillis()));
                                try {
                                    houseTypeSimilarityDOMapper.update(similarityDO);
                                    flag = true;
                                } catch (Exception e) {
                                    errorNum++;
                                    logger.error("web:更新相似度失败_HouseTypeSimilarityDOId:" + similarityDO.getId());
                                }
                            }
                            //更新状态设置为1
                            if (flag) {
                                ue.setUpdateStatus((byte) 1);
                                ue.setGmtUpdate(new Timestamp(System.currentTimeMillis()));
                                ue.setOperator(CommUtil.null2String(ue.getOperator()) + operatorName);
                                try {
                                    ueHouseTypeSimilarityMapper.update(ue);
                                    successNum++;
                                } catch (Exception e) {
                                    errorNum++;
                                    logger.error("ue:更新状态失败_UeHouseTypeSimilarityId:" + ue.getId());
                                }
                            }
                        }
                    }
                }
            }
        } while ((list != null && list.size() > 0) || errorNum > maxSynErrorNum - 1);
        logger.error("共更新成功:" + successNum + "条,失败:" + errorNum + "条");
        logger.error("更新户型相似度--------------------end-------------------------");
    }

    /**
     * 同步房间数据
     */
    @Override
    public void startSynRoom() {
        logger.error("更新房间相似度--------------------start-------------------------");
        int errorNum = 0;
        int successNum = 0;
        int i = 1;

        List<UeRoomSimilarity> list;

        List<RoomPointDO> listMapping1;
        List<RoomPointDO> listMapping2;
        RoomPointDO mapping1;
        RoomPointDO mapping2;

        Long webRoomPointId1;
        Long webRoomPointId2;

        do {
            //1 读相似度 20条
            PageHelper.startPage(i, 20);
            list = ueRoomSimilarityMapper.listRights();
            if (list != null && list.size() != 0) {
                //2 查询roomPoint
                for (UeRoomSimilarity ue : list) {
                    if (CommUtil.isEmptyString(ue.getSimilarity()) || CommUtil.string2Decimal(ue.getSimilarity()) == null) {
                        errorNum++;
                        logger.error("ue:相似度有误_similarity" + ue.getSimilarity());
                        continue;
                    }
                    listMapping1 = roomPointDOMapper.listByUePointId(ue.getUeRoomId());
                    listMapping2 = roomPointDOMapper.listByUePointId(ue.getUeSimilarRoomId());

                    if (listMapping1.isEmpty() || listMapping2.isEmpty()) {
                        errorNum++;
                        logger.error("web:不存在此条数据_roomPointDOId:" + ue.getUeRoomId() + "_UeSimilarRoomId:" + ue.getUeSimilarRoomId());
                        continue;
                    }
                    mapping1 = listMapping1.get(0);
                    mapping2 = listMapping2.get(0);

                    webRoomPointId1 = mapping1.getId();
                    webRoomPointId2 = mapping2.getId();

                    //3 写入开始 RoomSimilarityDO
                    Boolean flag = false;
                    List<RoomSimilarityDO> roomSimilarityDOS = roomSimilarityDOMapper.listByRoomPointId(webRoomPointId1, webRoomPointId2);
                    if (roomSimilarityDOS.isEmpty()) {
                        //添加
                        RoomSimilarityDO similarityDO = new RoomSimilarityDO();
                        similarityDO.setRoomPointId(webRoomPointId1);
                        similarityDO.setSimilarRoomPointId(webRoomPointId2);
                        similarityDO.setGmtCreate(new Timestamp(System.currentTimeMillis()));
                        similarityDO.setSimilarity(ue.getSimilarity().trim());
                        similarityDO.setDecimalSimilarity(CommUtil.string2Decimal(ue.getSimilarity()));
                        try {
                            roomSimilarityDOMapper.insert(similarityDO);
                            flag = true;
                        } catch (Exception e) {
                            errorNum++;
                            logger.error("web:写入错误_RoomPointId:" + webRoomPointId1 + "_SimilarRoomPointId:" + webRoomPointId2);
                        }
                    } else {
                        //更新
                        if (roomSimilarityDOS.size() > 1) {
                            Long similarityId = 0L;
                            int j = 0;
                            try {
                                for (RoomSimilarityDO similarityDO : roomSimilarityDOS) {
                                    //第一条不处理
                                    if (j != 0) {
                                        similarityId = similarityDO.getId();
                                        roomSimilarityDOMapper.delete(similarityDO.getId());
                                    }
                                    j++;
                                }
                            } catch (Exception e) {
                                errorNum++;
                                logger.error("web:删除相似度错误_RoomSimilarityDOId:" + similarityId);
                                //删除不了说明有关联，对此条数据不处理,一般来说在那边没有关联，可以删除
                                continue;
                            }
                        }
                        RoomSimilarityDO similarityDO = roomSimilarityDOS.get(0);
                        similarityDO.setSimilarity(ue.getSimilarity().trim());
                        similarityDO.setDecimalSimilarity(CommUtil.string2Decimal(ue.getSimilarity().trim()));
                        similarityDO.setGmtModified(new Timestamp(System.currentTimeMillis()));
                        try {
                            roomSimilarityDOMapper.update(similarityDO);
                            flag = true;
                        } catch (Exception e) {
                            errorNum++;
                            logger.error("web:更新相似度失败_RoomSimilarityDOId:" + similarityDO.getId());
                        }
                    }
                    //更新状态设置为1
                    if (flag) {
                        ue.setUpdateStatus((byte) 1);
                        ue.setGmtUpdate(new Timestamp(System.currentTimeMillis()));
                        ue.setOperator(CommUtil.null2String(ue.getOperator()) + operatorName);
                        try {
                            ueRoomSimilarityMapper.update(ue);
                            successNum++;
                        } catch (Exception e) {
                            errorNum++;
                            logger.error("ue:更新状态失败_UeRoomSimilarityId:" + ue.getId());
                        }
                    }
                }
            }
        }
        while ((list != null && list.size() > 0) || errorNum > maxSynErrorNum - 1);
        logger.error("共更新成功:" + successNum + "条,失败:" + errorNum + "条");
        logger.error("更新房间相似度--------------------end-------------------------");
    }

    @Override
    public void testJob1() {
        System.out.println("test job1 " + changeToTime());
    }

    @Override
    public void testJob2() {
        String s = "test job2 " + changeToTime();
        logger.error(s);
    }

    private String changeToTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
