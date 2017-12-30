package com.example.demo.entity.web;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * web端户型名
 *
 * @author jh
 * @date 2017/12/8 19:53
 */
@Data
public class HouseTypeDO implements Serializable {

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
     * 户型名
     */
    private String name;
}
