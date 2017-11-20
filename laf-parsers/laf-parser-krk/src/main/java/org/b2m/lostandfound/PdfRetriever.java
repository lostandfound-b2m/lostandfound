package org.b2m.lostandfound;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Class that retrieves URLs to PDF files on Krakow L&F Office site.
 * First, given main page address, it finds links to resource sites,
 * such as this one: https://www.bip.krakow.pl/zalaczniki/dokumenty/n/189876/karta,
 * and then, retrieves from it all attributes of PdfFile objects and returns them as List.
 *
 */
public class PdfRetriever {

    private static Logger log = Logger.getLogger(PdfRetriever.class);


   public static List<PdfFile> retrieve(String url) throws IOException, ParseException {
       List<PdfFile> resultList = new ArrayList<PdfFile>();
       Document doc = Jsoup.connect(url).get();
       Element content = doc.getElementById("mainDiv");

       /* Looking for URLs with regex */
       Elements links = content.getElementsByTag("a");
       for (Element link : links) {
           if (link.text().matches("Wykaz.*")) {
               PdfFile file = new PdfFile();
               file.setName(link.text());
               retrieveUrl(link.attr("abs:href"),file);
               resultList.add(file);
           }
       }
       return resultList;
   }

    private static void retrieveUrl(String url, PdfFile fileToBeFound) throws IOException, ParseException {
        log.info("Retrieving HTML");
        Document doc=Jsoup.connect(url).get();
        Element content = doc.getElementById("mainDiv");

        /* Attempt to find URL */
        Elements urls = content.getElementsByTag("a");
        for (Element urlToBeFound : urls)
            if (Objects.equals(urlToBeFound.text(), "zobacz"))
                fileToBeFound.setUrl(urlToBeFound.attr("abs:href"));

        /* Attempt to find last update's date */
        Elements labels = content.select("div[class~=(metka_td)[12]]");
        Elements infos = content.getElementsByClass("metka_td3");
        int nr = 0;
        for (Element label : labels) {
            if (Objects.equals(label.text(), "Data aktualizacji:")) {
                fileToBeFound.setLastUpdate(stringToDate(infos.get(nr).text()));
            }

            ++nr;

        }
    }

    private static Date stringToDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(strDate);
    }
}
