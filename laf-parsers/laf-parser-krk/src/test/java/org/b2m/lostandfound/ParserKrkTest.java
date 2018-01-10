package org.b2m.lostandfound;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ParserKrkTest {
    private List<Item> staticList = null;

    @Test
    public void getItemListfromStatic() throws Exception {
        ParserKrk test = new ParserKrk("src/main/resources/2017_2.pdf");
        staticList= test.getItemList();
        for (Item item: staticList) System.out.println(item);
        assertNotNull("empty list",staticList);
        assertEquals(staticList.get(0).getName(),"SASZETKA Z ZAW. ");
        assertEquals(staticList.size(),799);
        assertEquals(staticList.get(798).getCityCode(),"SA-03.5314.1.1457.2017");
    }


}