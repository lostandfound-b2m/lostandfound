package org.b2m.lostandfound;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] arg) throws IOException, ParseException {
        Service daoService = new Service();
        LostPropertyOffice Warsaw = new LostPropertyOffice("Warsaw", "biuro ztm warszawa", "502402103", "ztmWarszawa@gmail.com", "00-753");
        Date dateOfFound1 = new SimpleDateFormat("yyyyMMdd").parse("20171002");
        ItemInRepository item1 = new ItemInRepository("zegarek", dateOfFound1, "00-753", "Warszawa", Warsaw);
        List<ItemInRepository> items = new ArrayList<>();
        items.add(item1);
        //daoService.addItems((List<ItemInRepository>) items);
    }
}
