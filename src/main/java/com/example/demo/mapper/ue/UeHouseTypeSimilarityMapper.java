package com.example.demo.mapper.ue;

import com.example.demo.entity.ue.UeHouseTypeSimilarity;

import java.util.List;

/**
 * @author jh
 * @date 2017/12/7 15:48
 */
//@Mapper
public interface UeHouseTypeSimilarityMapper {

    List<UeHouseTypeSimilarity> getAll();

    UeHouseTypeSimilarity getOne(Long id);

    void insert(UeHouseTypeSimilarity entity);

    void update(UeHouseTypeSimilarity entity);

    void delete(Long id);

    /**
     * 返回 删除状态不为true,更新的状态 默认0
     *
     * @return
     */
    List<UeHouseTypeSimilarity> listRights();
}
