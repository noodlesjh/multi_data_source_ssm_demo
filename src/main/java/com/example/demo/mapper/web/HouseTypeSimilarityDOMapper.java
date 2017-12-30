package com.example.demo.mapper.web;


import com.example.demo.entity.web.HouseTypeSimilarityDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseTypeSimilarityDOMapper {

    List<HouseTypeSimilarityDO> getAll();

    HouseTypeSimilarityDO getOne(Long id);

    void insert(HouseTypeSimilarityDO entity);

    void update(HouseTypeSimilarityDO entity);

    void delete(Long id);

    /**
     * 根据2个houseTypeId 查询
     *
     * @param houseTypeId        houseTypeId
     * @param similarHouseTypeId similarHouseTypeId
     * @return List<HouseTypeSimilarityDO>
     */
    List<HouseTypeSimilarityDO> listByHouseTypeId(@Param("houseTypeId") Long houseTypeId, @Param("similarHouseTypeId") Long similarHouseTypeId);

//    /**
//     * 根据map查询
//     *
//     * @param map map
//     * @return List<HouseTypeSimilarityDO>
//     */
//    List<HouseTypeSimilarityDO> listByMap(HashMap<String, Object> map);
}