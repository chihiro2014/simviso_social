package com.simviso.spit.service;

import com.simviso.spit.dao.SpitDao;
import com.simviso.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/2/2019
 * @Email: liujch1996@gmail.com
 */
@Service
public class SpitService {

    private final SpitDao spitDao;

    private final IdWorker idWorker;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SpitService(SpitDao spitDao, IdWorker idWorker, MongoTemplate mongoTemplate) {
        this.spitDao = spitDao;
        this.idWorker = idWorker;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setShare(0);
        spit.setThumbup(0);
        spit.setComment(0);
        spit.setState("1");
        //判断当前吐槽是否有父节点
        if(spit.getParentid()!=null && !"".equals(spit.getParentid())){
            //给父节点吐槽的回复数加一
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
    }

    public void update(Spit spit){
        spitDao.save(spit);
    }

    public void deleteById(String id){
        spitDao.deleteById(id);
    }

    public Page<Spit> pageQuery(String parentid, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return spitDao.findByParentid(parentid, pageable);
    }

    public void addthumbup(String spitId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        //封装修改的数据内容
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
