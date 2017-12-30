package com.example.demo.entity.web;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * web端户型相似度
 *
 * @author jh
 * @date 2017/12/7 17:18
 */
@Data
public class HouseTypeSimilarityDO implements Serializable {

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
     * 户型id
     */
    private Long houseTypeId;

    /**
     * 另一个 户型id
     */
    private Long similarHouseTypeId;

    /**
     * String类型的 拿过来的完全值
     */
    private String similarity;

    /**
     * BigDecimal类型的  保留两位
     */
    private BigDecimal decimalSimilarity;

}
