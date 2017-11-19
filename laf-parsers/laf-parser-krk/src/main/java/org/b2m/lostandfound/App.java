package org.b2m.lostandfound;

import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.PropertyConfigurator;
/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws IOException, ParseException {
        // Configure Log4J
        //PropertyConfigurator.configure(App.class.getClassLoader().getResource("log4j.properties"));

        PdfFile file=new PdfRetriever().retrieve("https://www.bip.krakow.pl/zalaczniki/dokumenty/n/189876/karta");
        System.out.println("ok: " + file.getUrl());

    }
}
