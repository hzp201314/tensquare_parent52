package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是要把条件封装到哪个对象中。where类名=label.getid
             * @param query 封装的都是查询关键字，比如group by order by等
             * @param cb 用来封装条件对象 如果直接返回null，表示不需要任何条件；需要条件就需要封装成Predicate对象。
             * @return 封装结果
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new 一个集合来存放所有条件
                List<Predicate> list = new ArrayList<>();
                //条件封装
                //标签名称
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {//不为null并且不为空
                    //where labelname like "%小明%"
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                //状态
                if(label.getState()!=null&&!"".equals(label.getState())){
                    //where state = "1"
                    Predicate predicate=cb.equal(root.get("state").as(String.class),label.getState());
                    list.add(predicate);
                }
                //id
                if(label.getId()!=null&&!"".equals(label.getId())){
                    //where id = "1"
                    Predicate predicate=cb.equal(root.get("id").as(String.class),label.getId());
                    list.add(predicate);
                }
                /*
                //使用数量
                if(label.getCount()!=null&&!"".equals(label.getCount())){
                    //where count = "1"
                    Predicate predicate=cb.equal(root.get("count").as(String.class),label.getCount());
                    list.add(predicate);
                }
                */
                //是否推荐
                if(label.getRecommend()!=null&&!"".equals(label.getRecommend())){
                    //where recommend = "1"
                    Predicate predicate=cb.equal(root.get("recommend").as(String.class),label.getRecommend());
                    list.add(predicate);
                }
                /*
                //粉丝数
                if(label.getFans()!=null&&!"".equals(label.getFans())){
                    //where fans = "1"
                    Predicate predicate=cb.equal(root.get("fans").as(String.class),label.getFans());
                    list.add(predicate);
                }
                */
                //new 一个数组作为最终返回值的条件
                Predicate[] predicates = new Predicate[list.size()];
                //把list直接转换成数组
                predicates = list.toArray(predicates);

                //where labelname like "%小明%" and state="1"
                return cb.and(predicates);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        //封装一个分页对象
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，也就是要把条件封装到哪个对象中。where类名=label.getid
             * @param query 封装的都是查询关键字，比如group by order by等
             * @param cb 用来封装条件对象 如果直接返回null，表示不需要任何条件；需要条件就需要封装成Predicate对象。
             * @return 封装结果
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new 一个集合来存放所有条件
                List<Predicate> list = new ArrayList<>();
                //条件封装
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {//不为null并且不为空
                    //where labelname like "%小明%"
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if(label.getState()!=null&&!"".equals(label.getState())){
                    //where state = "1"
                    Predicate predicate=cb.equal(root.get("state").as(String.class),label.getState());
                    list.add(predicate);
                }
                //new 一个数组作为最终返回值的条件
                Predicate[] predicates = new Predicate[list.size()];
                //把list直接转换成数组
                predicates = list.toArray(predicates);

                //where labelname like "%小明%" and state="1"
                return cb.and(predicates);
            }
        }, pageable);
    }
}
