package org.b2m.lostandfound.persist.test;

import org.b2m.lostandfound.persist.ItemDao;
import org.b2m.lostandfound.persist.LostPropertyOffice;

import java.util.*;

public class TestDao {

    public LostPropertyOffice createLostPropertyOffice(String city,String officeName,String contactNumber,String email,String postalCode ){

        LostPropertyOffice lostPropertyOffice = new LostPropertyOffice(officeName,city,postalCode,contactNumber,email);
        return lostPropertyOffice;
    }

    public List<ItemDao> createItemList(LostPropertyOffice lostPropertyOffice) {

        Date dateOfFound1 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date dateOfFound2 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date dateOfFound3 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date dateOfFound4 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date dateOfFound5 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        ItemDao item1 = new ItemDao("zegarek", dateOfFound1, "00-753", "Warszawa", lostPropertyOffice);
        ItemDao item2 = new ItemDao("zegarek", dateOfFound2, "00-753", "Warszawa", lostPropertyOffice);
        ItemDao item3 = new ItemDao("plecak", dateOfFound3, "00-753", "Warszawa", lostPropertyOffice);
        ItemDao item4 = new ItemDao("walizka", dateOfFound4, "00-753", "Warszawa", lostPropertyOffice);
        ItemDao item5 = new ItemDao("kurtka", dateOfFound5, "00-753", "Warszawa", lostPropertyOffice);
        ItemDao item6 = new ItemDao("kurtka", dateOfFound2, "00-753", "Krakow", lostPropertyOffice);


        List<ItemDao> newItems = new ArrayList<>();
        //tworzymy liste przedmiotow(ktora normalnie zostanie zwrocona z parsera)
        newItems.add(item1);
        newItems.add(item2);
        newItems.add(item3);
        newItems.add(item4);
        newItems.add(item5);
        newItems.add(item6);

        return newItems;

    }


    }




















