package org.b2m.lostandfound.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;


public class LostPropertyDao implements LostPropertyRepository {

    private static SessionFactory sessionFactory;
    private Session currentSession;
    private Transaction currentTransaction;

    public LostPropertyDao() {
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void persist(Object entity) {
        getCurrentSession().save(entity);
    }

    public void update(Object entity) {
        getCurrentSession().update(entity);
    }

    public void delete(Object entity) {
        getCurrentSession().delete(entity);
    }

    public List<ItemDao> findByItemDescription(String itemDescription, String cityName) {
        List<ItemDao> items = (List<ItemDao>) getCurrentSession().createQuery("FROM LostItemDao WHERE itemDescription = :itemDescription AND cityName = :lostPropertyOffice").setParameter("itemDescription", itemDescription).setParameter("lostPropertyOffice", cityName).list();
        return items;

    }

    public List<ItemDao> returnAllItems(String officeName) {
        List<ItemDao> items = (List<ItemDao>) getCurrentSession().createQuery("FROM LostItemDao WHERE lostPropertyOffice.officeName = :officeName").setParameter("officeName", officeName).list();
        return items;

    }




}
