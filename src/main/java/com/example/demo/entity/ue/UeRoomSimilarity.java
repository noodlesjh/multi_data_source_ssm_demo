package com.example.demo.entity.ue;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ue4端房间相似度
 *
 * @author jh
 * @date 2017/12/7 15:30
 */
@Data
public class UeRoomSimilarity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 房间Id
     */
    private Long ueRoomId;

    /**
     * 相似房间Id
     */
    private Long ueSimilarRoomId;

    /**
     * 相似度（分数）
     */
    private String similarity;

    /**
     * 更新的状态
     */
    private byte updateStatus;

    /**
     * 是否删除(
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
