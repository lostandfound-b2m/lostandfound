package org.b2m.lostandfound;

import org.b2m.lostandfound.persist.LostPropertyOffice;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] arg) throws IOException {
        DaoService daoService = new DaoService();
        LostPropertyOffice Warsaw = new LostPropertyOffice("Warsaw", "biuro ztm warszawa", "502402103", "ztmWarszawa@gmail.com", "00-753");
        daoService.addItems(daoService.rewriteItemData(Warsaw));
    }
}
