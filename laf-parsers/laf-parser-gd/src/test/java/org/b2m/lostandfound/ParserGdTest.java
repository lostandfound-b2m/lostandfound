package org.b2m.lostandfound;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParserGdTest extends TestCase {

    @Test
    public void testParser() {
        ParserGd parserGd = new ParserGd("src/test/resources/gdansk.html");
        List<Item> items = parserGd.getItemList();

        assertEquals(402,items.size());
        assertEquals("Kwota pieniędzy",items.get(0).getName());
        assertEquals("Port Lotniczy Gdańsk",items.get(4).getFoundPlace());
        assertEquals("Portfel Real Madrdi z kwotą pieniędzy zł.",items.get(401).getName());

    }
}