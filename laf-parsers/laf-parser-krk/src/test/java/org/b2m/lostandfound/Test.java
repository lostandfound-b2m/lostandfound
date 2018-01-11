package org.b2m.lostandfound;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            ParserKrk test = new ParserKrk("laf-parsers/laf-parser-krk/src/main/resources/2017_1.pdf");
            List<Item> itemList = test.getItemList();
            for (Item e: itemList) {
                System.out.println(e.toString());
            }
        }
        catch (IOException e){
            System.out.println("klapa");
        }
    }
}
