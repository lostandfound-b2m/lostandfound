package org.b2m.lostandfound;


import java.text.ParseException;
import java.util.List;

public class MainTest {
    public static void main(String args[]) throws ParseException {
        TestDao test = new TestDao();

        LostPropertyOffice warsawLostPropertyOffice = test.createLostPropertyOffice("Warszawa","Warszawa","502402103","ztmWarszawa@gmail.com","00-753");
        LostPropertyOffice krakowLostPropertyOffice = test.createLostPropertyOffice("Kraków","Kraków","602452703","ztmKrakow@gmail.com","00-521");
        LostPropertyOffice gdanskLostPropertyOffice = test.createLostPropertyOffice("Gdańsk","Gdańsk","602452703","ztmGdansk@gmail.com","00-521");

        LostPropertyDao dao = new LostPropertyDao();
        dao.addLostPropertyOffice(warsawLostPropertyOffice);
        dao.addLostPropertyOffice(krakowLostPropertyOffice);
        dao.addLostPropertyOffice(gdanskLostPropertyOffice);

        LostPropertyOffice office = dao.findLostPropertyOffice("Gdańsk");
        LostPropertyOffice office2 = dao.findLostPropertyOffice("Kraków");
        System.out.println(office.getCity());
        System.out.println(office2.getCity());


       /*List<ItemInRepository> itemList = test.createItemList(warsawLostPropertyOffice);
        //LostPropertyDao dao = new LostPropertyDao();
        dao.addLostItems(itemList);
       /* itemList = dao.findByItemDescription("zegarek", "Warszawa");

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
