package org.b2m.lostandfound.persist.test;

import org.b2m.lostandfound.persist.ItemDao;
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

    public void persistLostItems(List<ItemDao> itemDaos) {

        lostPropertyDao.openCurrentSessionwithTransaction();
        for (ItemDao newItem : itemDaos)
        {
            lostPropertyDao.persist(newItem);
        }
        lostPropertyDao.closeCurrentSessionwithTransaction();

    }

    public void deleteLostItems(LostPropertyOffice lostPropertyOffice, List<ItemDao> itemDaos) {
        lostPropertyDao.openCurrentSessionwithTransaction();

        List<ItemDao> toRemove = new ArrayList<>();
        toRemove.addAll(itemDaos);
        itemDaos.removeAll(toRemove);

        lostPropertyDao.update(lostPropertyOffice);
        lostPropertyDao.closeCurrentSessionwithTransaction();

    }

    public List<ItemDao> findItems(String description, String cityName) {
        lostPropertyDao.openCurrentSessionwithTransaction();
        List<ItemDao> itemDaos = lostPropertyDao.findByItemDescription(description, cityName);
        lostPropertyDao.closeCurrentSessionwithTransaction();
        return itemDaos;
    }

    public List<ItemDao> returnAllItems(String officeName) {
        lostPropertyDao.openCurrentSessionwithTransaction();
        List<ItemDao> allItems = lostPropertyDao.returnAllItems(officeName);
        lostPropertyDao.closeCurrentSessionwithTransaction();
        return allItems;
    }





}
