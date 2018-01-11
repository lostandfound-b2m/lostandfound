package org.b2m.lostandfound;

import javax.xml.transform.Source;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String args[]) throws ParseException, IOException, NullPointerException {
        /*try {
            ParserWarsaw parserWarsaw = new ParserWarsaw();
            List<Item> itemList = parserWarsaw.getLostItemsFromParser();
        } catch (NullPointerException e)*/

            WawRetriever retriever = new WawRetriever();
            List<SourceFile> files = retriever.retrieveFiles();
            System.out.println(files.get(0).getName());
            System.out.println(files.get(0).getUrl());

            List<Item> items = retriever.retrieveItemsFromFiles(files);
            System.out.println(items.get(0).getName());
            System.out.println(items.get(1).getName());


    }
}

