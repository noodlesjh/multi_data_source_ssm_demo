package com.example.demo.mapper.ue;

import com.example.demo.entity.ue.UeRoomSimilarity;

import java.util.List;

/**
 * @author jh
 * @date 2017/12/7 15:48
 */
public interface UeRoomSimilarityMapper {

    List<UeRoomSimilarity> getAll();

    UeRoomSimilarity getOne(Long id);

    void insert(UeRoomSimilarity entity);

    void update(UeRoomSimilarity entity);

    void delete(Long id);

    /**
     * 返回 删除状态不为true,更新的状态 默认0
     *
     * @return list
     */
    List<UeRoomSimilarity> listRights();
}
