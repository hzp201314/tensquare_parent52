package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;


/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 最新回复
     * 默认GPQL语句,面向对象查询，不能出现表名.由于涉及到两张表联查，因此解决方案有两种:
     * 一是新建每张表的对象实体类，二是使用SQL语句（推荐做法）（需要设置nativeQuery = true）
     * nativeQuery = true则可以在value里面写SQL语句
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem,tb_pl WHERE id=problemid AND labelid=? ORDER BY replytime DESC ",nativeQuery = true)
    public Page<Problem> newlist(String labelid, Pageable pageable);

    /**
     * 最热回复
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem,tb_pl WHERE id=problemid AND labelid=? ORDER BY reply DESC ",nativeQuery = true)
    public Page<Problem> hotlist(String labelid, Pageable pageable);

    /**
     * 等待回答
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem,tb_pl WHERE id=problemid AND labelid=? AND reply=0 ORDER BY createtime DESC ",nativeQuery = true)
    public Page<Problem> waitlist(String labelid, Pageable pageable);
}
