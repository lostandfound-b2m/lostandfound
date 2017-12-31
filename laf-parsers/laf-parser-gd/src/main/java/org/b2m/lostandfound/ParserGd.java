package org.b2m.lostandfound;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ParserGd {

    private Document document;

    public ParserGd(URL url) {
        try {
            document = Jsoup.connect(url.toString()).get();
        }
        catch(IOException e) {
            document = null;
        }
    }

    public ParserGd(String string) {
        File file = new File(string);
        try {
            document = Jsoup.parse(file, "UTF-8", "http://bip.gdansk.pl/urzad-miejski/");
        }
        catch(IOException e) {
            document = null;
        }
    }

    public List<Item> getItemList() {
        List<Item> allItems = new LinkedList<>();
        Elements content = document.getElementsByClass("article");
        Elements rows = content.first().getElementsByTag("tr");
        for(int i=0; i<3; i++) {
            rows.remove(0);
        }
        for (Element row : rows) {
            Elements fields = row.getElementsByTag("td");
            if(fields.size()==5) {
                fields.remove(0);
                Date foundDate = getDate(fields.first().text());
                fields.remove(0);
                Date receiveDate = getDate(fields.first().text());
                fields.remove(0);
                String description = fields.first().text();
                fields.remove(0);
                String placeOfBeingFound = fields.first().text();
                Item item = new Item(description,foundDate,receiveDate,null,placeOfBeingFound,"Gdańsk");
                allItems.add(item);
            }
        }
        return allItems;
    }

    private Date getDate(String input) {
        Date date;
        if (input.length()>10) {
            input=input.substring(3);
        }
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            date = df.parse(input);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

}

