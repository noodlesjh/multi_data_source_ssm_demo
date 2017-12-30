package com.example.demo.mapper.web;

import com.example.demo.entity.web.RoomPointDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jh
 * @date 2017/12/8 9:59
 */
public interface RoomPointDOMapper {
    List<RoomPointDO> getAll();

    RoomPointDO getOne(Long id);

    void insert(RoomPointDO entity);

    void update(RoomPointDO entity);

    void delete(Long id);

    /**
     * 获取web端对应的RoomPointDO
     *
     * @param pointId pointId
     * @return list
     */
    List<RoomPointDO> listByUePointId(@Param("pointId") Long pointId);
}
