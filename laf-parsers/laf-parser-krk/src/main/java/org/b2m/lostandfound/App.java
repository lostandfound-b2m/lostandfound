package org.b2m.lostandfound;

import java.io.IOException;
import java.text.ParseException;

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

        PdfFile file=new PdfFile();
        new PdfRetriever().retrieve("https://www.bip.krakow.pl/zalaczniki/dokumenty/n/189876/karta", file);
        System.out.println("ok: " + file.getUrl());

    }
}
