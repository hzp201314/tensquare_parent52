package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    /**
     * 根据时间排序查询前六个推荐职位
     * @param state 状态：0：关闭     1:开启     2：推荐
     * @return 返回前六个推荐职位列表
     */
    public List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);//where state=? order by createtime

    /**
     * 根据时间排序查询最新的12个职位
     * Desc:倒序
     * @param state
     * @return
     */
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);//where state!=0 order by createtime
}
