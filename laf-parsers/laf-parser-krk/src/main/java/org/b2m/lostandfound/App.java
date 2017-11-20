package org.b2m.lostandfound;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
/**
 * As for now, we're only testing here our new classes.
 *
 */
public class App 
{

    public static void main( String[] args ) throws IOException, ParseException {
        // Configure Log4J
        //PropertyConfigurator.configure(App.class.getClassLoader().getResource("log4j.properties"));


        List<PdfFile> files = new PdfRetriever().retrieve("https://www.bip.krakow.pl/?dok_id=19964");
        for (PdfFile file : files) {
            System.out.println(file.getUrl());
        }


    }
}
