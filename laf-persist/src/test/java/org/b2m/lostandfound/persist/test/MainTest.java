package org.b2m.lostandfound.persist.test;

import org.b2m.lostandfound.persist.LostPropertyDao;
import org.b2m.lostandfound.persist.LostPropertyOffice;

import java.text.ParseException;

public class MainTest {
    public static void main(String args[]) throws ParseException {
        TestDao test = new TestDao();

        LostPropertyOffice warsawLostPropertyOffice = test.createLostPropertyOffice("Warsaw","Warszawa","502402103","ztmWarszawa@gmail.com","00-753");
        LostPropertyOffice krakowLostPropertyOffice = test.createLostPropertyOffice("Krakow","Kraków","602452703","ztmKrakow@gmail.com","00-521");
        LostPropertyOffice gdanskLostPropertyOffice = test.createLostPropertyOffice("Gdańsk","Gdańsk","602452703","ztmGdansk@gmail.com","00-521");

        LostPropertyDao dao = new LostPropertyDao();
        /*dao.addLostPropertyOffice(warsawLostPropertyOffice);
        dao.addLostPropertyOffice(krakowLostPropertyOffice);
        dao.addLostPropertyOffice(gdanskLostPropertyOffice);*/

        LostPropertyOffice office = dao.findLostPropertyOffice("Gdańsk");
        System.out.println(office.getCity());


      /*  List<ItemInRepository> itemList = test.createItemList(warsawLostPropertyOffice);
        LostPropertyDao dao = new LostPropertyDao();
        dao.addLostItems(itemList);
        itemList = dao.findByItemDescription("zegarek", "Warszawa");

        for (ItemInRepository item : itemList) {
            System.out.println(item.getName());
            System.out.println(item.getFindDate());
            System.out.println(item.getCityName());
        }
        itemList = null;
        itemList = dao.returnAllItemsFromOffice("Warszawa");
        System.out.println("All items.");
        for (ItemInRepository item : itemList) {
            System.out.println(item.getName());
            System.out.println(item.getFindDate());
            System.out.println(item.getCityName());
        }

        //dao.deleteLostItems(itemList);
        //dao.deleteLostPropertyOffice(warsawLostPropertyOffice);
*/


    }
}
