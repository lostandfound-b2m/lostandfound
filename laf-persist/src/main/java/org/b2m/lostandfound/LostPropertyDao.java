package org.b2m.lostandfound;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
/**
 * Klasa LostPropertyDao jest implementacją interfejsu LostPropertyRepository, korzystamy w niej z Hibernate'a
 * który zapewnia translację danych pomiędzy relacyjną bazą danych a światem obiektowym. W funkcjach zwracających
 * obiekty lub listy obiektów używamy Hibernate Query Language (HQL)  - obiektowego języka zapytań.
 *
 */

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

    public void addLostItems(List<ItemInRepository> itemInRepositories) {
        openCurrentSessionwithTransaction();
        for (ItemInRepository newItem : itemInRepositories) {
            persist(newItem);
        }
        closeCurrentSessionwithTransaction();
    }

    public void addLostPropertyOffice(LostPropertyOffice office) {
        openCurrentSessionwithTransaction();
        persist(office);
        closeCurrentSessionwithTransaction();
    }

    public void addSourceFile(SourceFileInRepository sourceFileInRepository){
        openCurrentSessionwithTransaction();
        persist(sourceFileInRepository);
        closeCurrentSessionwithTransaction();
    }

    public List<SourceFileInRepository> getSourceFiles(String officeName) {
        openCurrentSessionwithTransaction();
        LostPropertyOffice lostPropertyOffice =(LostPropertyOffice) getCurrentSession()
                .createQuery("FROM LostPropertyOffice WHERE city = :lostPropertyOffice")
                .setParameter("lostPropertyOffice", officeName)
                .uniqueResult();
        List<SourceFileInRepository> items = (List<SourceFileInRepository>) getCurrentSession()
                .createQuery("FROM SourceFileInRepository WHERE lostPropertyOffice = :lostPropertyOffice")
                .setParameter("lostPropertyOffice", lostPropertyOffice)
                .list();
        closeCurrentSessionwithTransaction();
        return items;
    }

    public List<ItemInRepository> getItemsListedOnSourceFile(SourceFileInRepository file) {
        openCurrentSessionwithTransaction();
        List<ItemInRepository> items = (List<ItemInRepository>) getCurrentSession()
                .createQuery("FROM ItemInRepository WHERE sourceFile = :sourceFile")
                .setParameter("sourceFile", file)
                .list();
        closeCurrentSessionwithTransaction();
        return items;
    }

    public void deleteItemsListedOnSourceFile(SourceFileInRepository file) {
        openCurrentSessionwithTransaction();
        List<ItemInRepository> items = (List<ItemInRepository>) getCurrentSession()
                .createQuery("FROM ItemInRepository WHERE sourceFile = :sourceFile")
                .setParameter("sourceFile", file)
                .list();
        for (ItemInRepository item : items) {
            delete(item);
        }
        closeCurrentSessionwithTransaction();
    }

    public List<ItemInRepository> findByItemDescription(String itemDescription, String cityName) {
        openCurrentSessionwithTransaction();
        List<ItemInRepository> items = (List<ItemInRepository>) getCurrentSession()
                .createQuery("FROM ItemInRepository WHERE name like :name AND cityName = :lostPropertyOffice")
                .setParameter("name", "%"+itemDescription+"%").setParameter("lostPropertyOffice", cityName)
                .list();
        closeCurrentSessionwithTransaction();
        return items;

    }
    public LostPropertyOffice findLostPropertyOffice(String cityName) {
        openCurrentSessionwithTransaction();
        LostPropertyOffice lostPropertyOffice =(LostPropertyOffice) getCurrentSession()
                .createQuery("FROM LostPropertyOffice WHERE city = :lostPropertyOffice")
                .setParameter("lostPropertyOffice", cityName)
                .uniqueResult();
        closeCurrentSessionwithTransaction();
        return lostPropertyOffice;

    }

    public List<ItemInRepository> returnAllItemsFromOffice(String officeName) {
        openCurrentSessionwithTransaction();
        LostPropertyOffice lostPropertyOffice =(LostPropertyOffice) getCurrentSession()
                .createQuery("FROM LostPropertyOffice WHERE officeName = :lostPropertyOffice")
                .setParameter("lostPropertyOffice", officeName)
                .uniqueResult();
        List<ItemInRepository> items = (List<ItemInRepository>) getCurrentSession()
                .createQuery("FROM ItemInRepository WHERE lostPropertyOffice = :lostPropertyOffice")
                .setParameter("lostPropertyOffice", lostPropertyOffice)
                .list();
        closeCurrentSessionwithTransaction();
        return items;
    }

    public List<ItemInRepository> returnAllItems() {
        openCurrentSessionwithTransaction();
        List<ItemInRepository> items = (List<ItemInRepository>) getCurrentSession()
                .createQuery("FROM ItemInRepository")
                .list();
        closeCurrentSessionwithTransaction();
        return items;

    }

    public void deleteLostPropertyOffice(String officeName) {
        openCurrentSessionwithTransaction();
        LostPropertyOffice office = (LostPropertyOffice) getCurrentSession()
                .createQuery("FROM LostPropertyOffice WHERE officeName = :officeName")
                .setParameter("officeName",officeName);
        delete(office);
        closeCurrentSessionwithTransaction();
    }


    public void deleteLostItems(List<ItemInRepository> items) {
        openCurrentSessionwithTransaction();
        for (ItemInRepository item : items)
            delete(item);
        closeCurrentSessionwithTransaction();
    }

    public void deleteSourceFile(SourceFileInRepository sourceFileInRepository) {
        openCurrentSessionwithTransaction();
        delete(sourceFileInRepository);
        closeCurrentSessionwithTransaction();
    }






}
