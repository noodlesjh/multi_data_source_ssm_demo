package com.example.demo.entity.web;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * web端和ue4端对应房间点对应表
 *
 * @author jh
 * @date 2017/12/8 9:55
 */
@Data
public class RoomPointDO implements Serializable {

    private Long id;

    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    private Timestamp gmtModified;

    /**
     * 删除状态
     */
    private Boolean deleteStatus;

    /**
     * 对方 point id
     */
    private Long pointId;

}
