package com.example.demo.mapper.ue;

import com.example.demo.entity.ue.WebUeHouseTypeMapping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jh
 * @date 2017/12/7 17:10
 */
public interface WebUeHouseTypeMappingMapper {

    List<WebUeHouseTypeMapping> getAll();

    WebUeHouseTypeMapping getOne(Long id);

    void insert(WebUeHouseTypeMapping entity);

    void update(WebUeHouseTypeMapping entity);

    void delete(Long id);

    List<WebUeHouseTypeMapping> listByUeHouseTypeId(@Param(value = "ueHouseTypeId") Long ueHouseTypeId);
}
