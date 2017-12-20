package org.b2m.lostandfound.persist.test;

import org.b2m.lostandfound.persist.LostItemDao;
import org.b2m.lostandfound.persist.LostPropertyDao;
import org.b2m.lostandfound.persist.LostPropertyOffice;

import java.util.ArrayList;
import java.util.List;

public class TestLostPropertyService {


    private static LostPropertyDao lostPropertyDao;

    public TestLostPropertyService() {

        lostPropertyDao = new LostPropertyDao();
    }

    public void persistLostPropertyOffice(LostPropertyOffice lostPropertyOffice){
        LostPropertyDao lostPropertyDao = new LostPropertyDao();
        lostPropertyDao.openCurrentSessionwithTransaction();
        lostPropertyDao.persist(lostPropertyOffice);
        lostPropertyDao.closeCurrentSessionwithTransaction();

    }

    public void persistLostItems(List<LostItemDao> lostItemDaos) {

        lostPropertyDao.openCurrentSessionwithTransaction();
        for (LostItemDao newItem : lostItemDaos)
        {
            lostPropertyDao.persist(newItem);
        }
        lostPropertyDao.closeCurrentSessionwithTransaction();

    }

    public void deleteLostItems(LostPropertyOffice lostPropertyOffice, List<LostItemDao> lostItemDaos) {
        lostPropertyDao.openCurrentSessionwithTransaction();

        List<LostItemDao> toRemove = new ArrayList<>();
        toRemove.addAll(lostItemDaos);
        lostItemDaos.removeAll(toRemove);

        lostPropertyDao.update(lostPropertyOffice);
        lostPropertyDao.closeCurrentSessionwithTransaction();

    }

    public List<LostItemDao> findItems(String description, String cityName) {
        lostPropertyDao.openCurrentSessionwithTransaction();
        List<LostItemDao> lostItemDaos = lostPropertyDao.findByItemDescription(description, cityName);
        lostPropertyDao.closeCurrentSessionwithTransaction();
        return lostItemDaos;
    }

    public List<LostItemDao> returnAllItems(String officeName) {
        lostPropertyDao.openCurrentSessionwithTransaction();
        List<LostItemDao> allItems = lostPropertyDao.returnAllItems(officeName);
        lostPropertyDao.closeCurrentSessionwithTransaction();
        return allItems;
    }





}
