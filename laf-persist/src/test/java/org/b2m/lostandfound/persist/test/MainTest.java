package org.b2m.lostandfound.persist.test;

import org.b2m.lostandfound.persist.LostItemDao;
import org.b2m.lostandfound.persist.LostPropertyOffice;

import java.util.List;

public class MainTest {
    public static void main(String args[]){
        TestDao test = new TestDao();

        LostPropertyOffice warsawLostPropertyOffice = test.createLostPropertyOffice("Warsaw","biuro ztm warszawa","502402103","ztmWarszawa@gmail.com","00-753");
        LostPropertyOffice krakowLostPropertyOffice = test.createLostPropertyOffice("Krakow","biuro ztm krakow","602452703","ztmKrakow@gmail.com","00-521");

        List<LostItemDao> itemList = test.createItemList(warsawLostPropertyOffice);

        TestLostPropertyService lostPropertyService = new TestLostPropertyService();

        lostPropertyService.persistLostPropertyOffice(warsawLostPropertyOffice);
        lostPropertyService.persistLostPropertyOffice(krakowLostPropertyOffice);
        lostPropertyService.persistLostItems(itemList);


        //test.checkItemsInOffice(warsawLostPropertyOffice);

        //usuwamy wszystkie przedmioty z biura
        //ostPropertyService.deleteLostItems(warsawLostPropertyOffice,warsawLostPropertyOffice.getLostItems());
        List<LostItemDao> foundItems = lostPropertyService.findItems("kurtka", "Warszawa");
        for (LostItemDao item : foundItems) {
            System.out.println(item.getName());
            System.out.println(item.getFindDate());
            System.out.println(item.getCityName());
        }

        //test.checkItemsInOffice(warsawLostPropertyOffice);


    }
}
