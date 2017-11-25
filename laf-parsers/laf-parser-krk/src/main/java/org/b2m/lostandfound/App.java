package org.b2m.lostandfound;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


/**
 * As for now, we're only testing here our new classes.
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, ParseException {
        List<SourceFile> files = new KrkRetriever().retrieve("https://www.bip.krakow.pl/?dok_id=19964");
        for (SourceFile file : files) {
            System.out.println(file.getUrl());
        }
    }
}
