package org.b2m.lostandfound.persist.test;

import org.b2m.lostandfound.persist.ItemDao;
import org.b2m.lostandfound.persist.LostPropertyOffice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestDao {

    public LostPropertyOffice createLostPropertyOffice(String city,String officeName,String contactNumber,String email,String postalCode ){

        return new LostPropertyOffice(officeName,city,postalCode,contactNumber,email);

    }

    public List<ItemDao> createItemList(LostPropertyOffice lostPropertyOffice) throws ParseException {

       /* LocalDate dateOfFound1 = LocalDate.of(2017, 10, 2);
        LocalDate dateOfFound2 = LocalDate.of(2017, 1, 20);
        LocalDate dateOfFound3 = LocalDate.of(2017, 8, 5);
        LocalDate dateOfFound4 = LocalDate.of(2017, 4, 19);
        LocalDate dateOfFound5 = LocalDate.of(2017, 2, 28); */

        Date dateOfFound1 = new SimpleDateFormat("yyyyMMdd").parse("20171002");
        Date dateOfFound2 = new SimpleDateFormat("yyyyMMdd").parse("20170805");
        Date dateOfFound3 = new SimpleDateFormat("yyyyMMdd").parse("20170831");
        Date dateOfFound4 = new SimpleDateFormat("yyyyMMdd").parse("20170613");
        Date dateOfFound5 = new SimpleDateFormat("yyyyMMdd").parse("20170901");


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




















