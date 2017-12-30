package com.example.demo.mapper.web;


import com.example.demo.entity.web.RoomSimilarityDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomSimilarityDOMapper {

    List<RoomSimilarityDO> getAll();

    RoomSimilarityDO getOne(Long id);

    void insert(RoomSimilarityDO entity);

    void update(RoomSimilarityDO entity);

    void delete(Long id);

    /**
     * 根据2个point查询
     *
     * @param roomPointId        roomPointId
     * @param similarRoomPointId similarRoomPointId
     * @return List<RoomSimilarityDO>
     */
    List<RoomSimilarityDO> listByRoomPointId(@Param("roomPointId") Long roomPointId, @Param("similarRoomPointId") Long similarRoomPointId);

}