package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Enterprise;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{
    /**
     * 查询热门企业
     * @param ishot 是否热门 1=是；  0=否
     * @return
     */
    //是否热门，相当于在Service层封装的条件,spingdatajpa简单
    //<==>where ishot=?
    public List<Enterprise> findByIshot(String ishot);
}
