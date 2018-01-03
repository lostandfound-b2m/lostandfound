package org.b2m.lostandfound;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ParserKrkTest {
    private List<Item> staticList = null;
    private List<Item> webList = null;

    @Before
    public void getItemListfromStatic() throws Exception {
        ParserKrk test = new ParserKrk("src/main/resources/2017_2.pdf");
        staticList= test.getItemList();
        for (Item item: staticList) System.out.println(item);
        assertNotNull("empty list",staticList);
    }

    @Before
    public void getItemListfromWeb() throws Exception {
        KrkRetriever retriever = new KrkRetriever();
        List<SourceFile> files = retriever.retrieveFiles();
        SourceFile sourceFile = null;
        for (SourceFile s :files) {
            if(s.getName().equals("Wykaz 2017 r.")){
                sourceFile = s;
                break;
            }
        }
        assertNotNull("no such file",sourceFile);
        files = new LinkedList<>();
        files.add(sourceFile);
        webList = retriever.retrieveItemsFromFiles(files);
        for (Item item: webList) System.out.println(item);
    }

    @Test
    public void checkList() {
        assertNotNull("lists are not the same",webList.equals(staticList));
    }
}