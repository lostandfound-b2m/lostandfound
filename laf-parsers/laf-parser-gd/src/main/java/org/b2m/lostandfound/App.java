package org.b2m.lostandfound;

import java.net.MalformedURLException;
import java.net.URL;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws MalformedURLException {
        System.out.println( "Hello World!" );
        ParserGd parser = new ParserGd(new URL("http://bip.gdansk.pl/urzad-miejski/Informacje-biura-rzeczy-znalezionych,a,50360"));
        parser.getItemList();
    }
}
