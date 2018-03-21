package com.wjb.dao;

import com.wjb.model.UserEntity;
import com.wjb.utils.KVEntity;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDao {

    /**
     * 查询Stage表所有数据
     * */
    public List<UserEntity> query(String hql,List<KVEntity> parameter) {
        Session session = null;
        List<UserEntity> list = null;
        try {
            //实例化Configuration，这行代码默认加载hibernate.cfg.xml文件
            Configuration conf = new Configuration().configure();
            //以Configuration创建SessionFactory
            SessionFactory sf = conf.buildSessionFactory();
            //实例化Session
            session = sf.openSession();
            Query query = session.createQuery(hql);
            if(parameter!=null){
                for (KVEntity p:parameter) {
                    query=query.setParameter(p.key,p.value);
                }
            }

            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return list;
    }

    /**
     * 查询Stage表所有数据
     * */
    public  boolean insert(UserEntity user) {
        Session session = null;
        try {
            //实例化Configuration，这行代码默认加载hibernate.cfg.xml文件
            Configuration conf = new Configuration().configure();
            //以Configuration创建SessionFactory
            SessionFactory sf = conf.buildSessionFactory();
            //实例化Session
            session = sf.openSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }
}
