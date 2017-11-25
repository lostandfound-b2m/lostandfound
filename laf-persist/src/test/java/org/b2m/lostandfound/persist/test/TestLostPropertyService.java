package org.b2m.lostandfound.persist.test;

import org.b2m.lostandfound.persist.LostItem;
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

    public void persistLostItems(LostPropertyOffice lostPropertyOffice, List<LostItem> lostItems){

        lostPropertyDao.openCurrentSessionwithTransaction();
        for (LostItem newItem : lostItems)
        {
            lostPropertyOffice.addLostItem(newItem);
        }
        lostPropertyDao.update(lostPropertyOffice);
        lostPropertyDao.closeCurrentSessionwithTransaction();

    }

    public void deleteLostItems(LostPropertyOffice lostPropertyOffice,List<LostItem> lostItems) {
        lostPropertyDao.openCurrentSessionwithTransaction();

        List<LostItem> toRemove = new ArrayList<>();
        for (LostItem olditem : lostItems) {
            toRemove.add(olditem);
        }
        lostItems.removeAll(toRemove);

        lostPropertyDao.update(lostPropertyOffice);
        lostPropertyDao.closeCurrentSessionwithTransaction();

    }





}
