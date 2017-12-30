package com.example.demo.entity.ue;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ue端户型相似度
 *
 * @author jh
 * @date 2017/12/7 15:30
 */
@Data
public class UeHouseTypeSimilarity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 户型Id
     */
    private Long ueHouseTypeId;

    /**
     * 相似户型Id
     */
    private Long ueSimilarHouseTypeId;

    /**
     * 相似度（分数）
     */
    private String similarity;

    /**
     * 更新的状态 默认0
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
