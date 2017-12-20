package org.b2m.lostandfound;

import org.b2m.lostandfound.parser.warsaw.ParserWarsaw;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String args[]) throws ParseException, IOException, NullPointerException {
        try {
            ParserWarsaw parserWarsaw = new ParserWarsaw();
            List<Item> items = parserWarsaw.getLostItemsFromParser();
            for (Item item : items)
                System.out.println(item);
        } catch (NullPointerException e) {
        }
        ;
    }

}
