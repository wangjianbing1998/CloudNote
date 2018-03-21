package com.wjb.dao;

import com.wjb.model.NoteEntity;
import com.wjb.utils.KVEntity;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class NoteDao {

    /**
     * 查询Stage表所有数据
     * */
    public List<NoteEntity> query(String hql, List<KVEntity> parameter) {
        Session session = null;
        List<NoteEntity> list = null;
        try {
            Configuration conf = new Configuration().configure();
            SessionFactory sf = conf.buildSessionFactory();
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
    public  boolean insert(NoteEntity note) {
        Session session = null;
        try {
            //实例化Configuration，这行代码默认加载hibernate.cfg.xml文件
            Configuration conf = new Configuration().configure();
            //以Configuration创建SessionFactory
            SessionFactory sf = conf.buildSessionFactory();
            //实例化Session
            session = sf.openSession();
            Transaction tx = session.beginTransaction();
            session.save(note);
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


    public int delete(int note_id) {
        Session session = null;
        int result = 0;
        try {
            Configuration conf = new Configuration().configure();
            SessionFactory sf = conf.buildSessionFactory();
            session = sf.openSession();
            Query query = session.createQuery("DELETE FROM NoteEntity note WHERE id=?").setInteger(0,note_id);
            result = query.executeUpdate();;
        } catch (HibernateException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (session != null) {
                session.close();
                return result;
            }
        }
        return result;
    }


    public int update(String hql, List<KVEntity> parameter) {
        Session session = null;
        int result=0;
        try {
            Configuration conf = new Configuration().configure();
            SessionFactory sf = conf.buildSessionFactory();
            session = sf.openSession();

            Transaction trans=session.beginTransaction();
            Query query = session.createQuery(hql);
            if(parameter!=null){
                for (KVEntity p:parameter) {
                    query=query.setParameter(p.key,p.value);
                }
            }

            result = query.executeUpdate();
            trans.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (session != null) {
                session.close();
                return result;
            }
        }
        return result;
    }
}
