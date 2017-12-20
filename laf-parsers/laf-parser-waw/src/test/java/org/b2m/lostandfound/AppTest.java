package org.b2m.lostandfound;

import org.b2m.lostandfound.parser.warsaw.ParserWarsaw;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String args[]) throws IOException {
        ParserWarsaw parserWarsaw = new ParserWarsaw();
        parserWarsaw.getData();
    }
}
