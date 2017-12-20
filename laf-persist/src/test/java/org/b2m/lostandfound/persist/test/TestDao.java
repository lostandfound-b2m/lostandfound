package org.b2m.lostandfound.persist.test;

import org.b2m.lostandfound.persist.LostItemDao;
import org.b2m.lostandfound.persist.LostPropertyOffice;

import java.util.*;

public class TestDao {

    public LostPropertyOffice createLostPropertyOffice(String city,String officeName,String contactNumber,String email,String postalCode ){

        LostPropertyOffice lostPropertyOffice = new LostPropertyOffice(officeName,city,postalCode,contactNumber,email);
        return lostPropertyOffice;
    }

    public List<LostItemDao> createItemList(LostPropertyOffice lostPropertyOffice) {

        Date dateOfFound1 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date dateOfFound2 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date dateOfFound3 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date dateOfFound4 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date dateOfFound5 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        LostItemDao item1 = new LostItemDao("zegarek", dateOfFound1, "00-753", "Warszawa", lostPropertyOffice);
        LostItemDao item2 = new LostItemDao("zegarek", dateOfFound2, "00-753", "Warszawa", lostPropertyOffice);
        LostItemDao item3 = new LostItemDao("plecak", dateOfFound3, "00-753", "Warszawa", lostPropertyOffice);
        LostItemDao item4 = new LostItemDao("walizka", dateOfFound4, "00-753", "Warszawa", lostPropertyOffice);
        LostItemDao item5 = new LostItemDao("kurtka", dateOfFound5, "00-753", "Warszawa", lostPropertyOffice);
        LostItemDao item6 = new LostItemDao("kurtka", dateOfFound2, "00-753", "Krakow", lostPropertyOffice);


        List<LostItemDao> newItems = new ArrayList<>();
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




















