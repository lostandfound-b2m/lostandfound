package org.b2m.lostandfound;

import java.io.IOException;
/*
 * Otrzymujemy plik Output.json z listą wszystkich zgubionych obiektów,
 * wypisanych na stronie krakowskiego biura.
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        KrkRetriever KrkRetriever = new KrkRetriever();
        new ItemListGetter().getList(KrkRetriever);

    }
}
