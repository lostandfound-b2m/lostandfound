package org.b2m.lostandfound.persist.test;

import org.b2m.lostandfound.persist.LostItem;
import org.b2m.lostandfound.persist.LostPropertyOffice;
import java.util.*;

public class TestDao {

    public LostPropertyOffice createLostPropertyOffice(String city,String officeName,String contactNumber,String email,String postalCode ){

        LostPropertyOffice lostPropertyOffice = new LostPropertyOffice(officeName,city,postalCode,contactNumber,email);
        return lostPropertyOffice;
    }

    public List<LostItem> createItemList(LostPropertyOffice lostPropertyOffice) {

        Date dateOfFound1 = new GregorianCalendar(2017, Calendar.MARCH, 7).getTime();
        Date dateOfFound2 = new GregorianCalendar(2017, Calendar.MAY, 9).getTime();
        Date dateOfFound3 = new GregorianCalendar(2017, Calendar.APRIL, 14).getTime();
        Date dateOfFound4 = new GregorianCalendar(2017, Calendar.NOVEMBER, 20).getTime();
        Date dateOfFound5 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 30).getTime();

        LostItem item1 = new LostItem("zegarek", dateOfFound1, "00-753", "Warszawa", lostPropertyOffice);
        LostItem item2 = new LostItem("zegarek", dateOfFound2, "00-753", "Warszawa", lostPropertyOffice);
        LostItem item3 = new LostItem("plecak", dateOfFound3, "00-753", "Warszawa", lostPropertyOffice);
        LostItem item4 = new LostItem("walizka", dateOfFound4, "00-753", "Warszawa", lostPropertyOffice);
        LostItem item5 = new LostItem("kurtka", dateOfFound5, "00-753", "Warszawa", lostPropertyOffice);


        List<LostItem> newItems = new ArrayList<>();
        //tworzymy liste przedmiotow(ktora normalnie zostanie zwrocona z parsera)
        newItems.add(item1);
        newItems.add(item2);
        newItems.add(item3);
        newItems.add(item4);
        newItems.add(item5);

        return newItems;

    }

    public void checkItemsInOffice(LostPropertyOffice lostPropertyOffice) {
           List<LostItem> currentItemsInOffice;
           currentItemsInOffice = lostPropertyOffice.getLostItems();

           System.out.println("Stan przedmiotow w biurze:");
           if (currentItemsInOffice.isEmpty() == true)
               System.out.println("Brak przedmiotow w bazie danych");
           else
               for (LostItem currentItem : currentItemsInOffice) {

                   System.out.println(currentItem.getName());
               }
    }


    }




















