package com.example.demo.mapper;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 分页插件
 *
 * @author jh
 * @date 2017/12/8 14:18
 */
@Configuration
public class PageHelperConfiguration {

    @Bean
    public static PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        props.setProperty("offsetAsPageNum", "true");
        props.setProperty("rowBoundsWithCount", "true");
        //通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的结果。
        props.setProperty("pageSizeZero", "true");
        props.setProperty("dialect", "mysql");
        pageHelper.setProperties(props);
        return pageHelper;
    }
}
