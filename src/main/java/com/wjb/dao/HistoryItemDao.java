package com.wjb.dao;

import com.wjb.model.HistoryItemEntity;
import com.wjb.utils.KVEntity;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HistoryItemDao {

    /**
     * 查询Stage表所有数据
     * */
    public List<HistoryItemEntity> query(String hql, List<KVEntity> parameter) {
        Session session = null;
        List<HistoryItemEntity> list = null;
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
    public  boolean insert(HistoryItemEntity HistoryItem) {
        Session session = null;
        try {
            //实例化Configuration，这行代码默认加载hibernate.cfg.xml文件
            Configuration conf = new Configuration().configure();
            //以Configuration创建SessionFactory
            SessionFactory sf = conf.buildSessionFactory();
            //实例化Session
            session = sf.openSession();
            Transaction tx = session.beginTransaction();
            session.save(HistoryItem);
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


    public int delete(int historyItem_id) {
        Session session = null;
        int result = 0;
        try {
            Configuration conf = new Configuration().configure();
            SessionFactory sf = conf.buildSessionFactory();
            session = sf.openSession();
            Query query = session.createQuery("DELETE FROM HistoryItemEntity HistoryItem WHERE history__id=?").setInteger(0,historyItem_id);
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

    public int deleteAll(int user_id) {
        Session session = null;
        int result = 0;
        try {
            Configuration conf = new Configuration().configure();
            SessionFactory sf = conf.buildSessionFactory();
            session = sf.openSession();
//            Query query = session.createSQLQuery("delete from HistoryItemEntity history_item where history_item.notes_id in(select  notes_id from NoteEntity note where note.userId=?)").setInteger(0,user_id);
            Query query = session.createQuery("DELETE FROM HistoryItemEntity HistoryItem WHERE notes_id in (select id from NoteEntity where userId= ?)").setInteger(0,user_id);
            result = query.executeUpdate();
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
