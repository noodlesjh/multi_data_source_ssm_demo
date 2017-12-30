package com.example.demo.entity.ue;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ue4端和web端的户型对照表
 *
 * @author jh
 * @date 2017/12/7 17:01
 */
@Data
public class WebUeHouseTypeMapping implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 网站户型Id
     */
    private Long webHouseTypeId;

    /**
     * ue4端存的户型Id
     */
    private Long ueHouseTypeId;


    /**
     * 更新的状态
     */
    private byte updateStatus;

    /**
     * 是否删除
     */
    private Boolean deleteStatus;

    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    /**
     * 修改时间
     */
    private Timestamp gmtUpdate;

    /**
     * 最后修改人
     */
    private String operator;
}
