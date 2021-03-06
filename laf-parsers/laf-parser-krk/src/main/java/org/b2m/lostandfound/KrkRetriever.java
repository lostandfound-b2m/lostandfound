package org.b2m.lostandfound;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/*
 * Class that retrieves URLs to PDF files on Krakow L&F Office site.
 * First, given main page address, it finds links to resource sites,
 * such as this one: https://www.bip.krakow.pl/zalaczniki/dokumenty/n/189876/karta,
 * and then, retrieves from it all attributes of SourceFile objects and returns them as List.
 *
 */
public class KrkRetriever implements Retriever {

    public List<Item> retrieveItemsFromFiles(List<SourceFile> files) throws IOException{
        List<Item> items = new ArrayList<>();
        for (SourceFile file : files) {
            List<Item> itemsFromFile = new ParserKrk(new URL(file.getUrl())).getItemList();
            for (Item item : itemsFromFile) {
                item.setFile(file);
            }
            items.addAll(itemsFromFile);
        }
        return items;
    }

   public List<SourceFile> retrieveFiles() throws IOException{
       String url = "https://www.bip.krakow.pl/?dok_id=19964";
       List<SourceFile> resultList = new ArrayList<>();
       Document doc;

       doc = Jsoup.connect(url).get();

       Element content = doc.getElementById("mainDiv");

       /* Looking for URLs with regex */
       Elements links = content.getElementsByTag("a");
       for (Element link : links) {
           if (link.text().matches("Wykaz.*")) {
               SourceFile file = new SourceFile();
               file.setName(link.text());
               try {
                   retrieveUrl(link.attr("abs:href"), file);
               }
               catch(IOException e) {
                   return null;
               }
               resultList.add(file);
           }
       }
       for (SourceFile file : resultList) {
           file.setOfficeName("Kraków");
       }
       return resultList;
   }

    private static void retrieveUrl(String url, SourceFile fileToBeFound) throws IOException {

        Document doc=Jsoup.connect(url).get();
        Element content = doc.getElementById("mainDiv");

        /* Attempt to find URL */
        Elements urls = content.getElementsByTag("a");
        for (Element urlToBeFound : urls)
            if (Objects.equals(urlToBeFound.text(), "zobacz"))
                fileToBeFound.setUrl(urlToBeFound.attr("abs:href"));

        /* Attempt to find last update's date to be set as updateChecker*/
        Elements labels = content.select("div[class~=(metka_td)[12]]");
        Elements infos = content.getElementsByClass("metka_td3");
        int nr = 0;
        for (Element label : labels) {
            if (Objects.equals(label.text(), "Data aktualizacji:")) {
                fileToBeFound.setUpdateChecker(Integer.toString(infos.get(nr).text().hashCode()));
            }
            ++nr;
        }
    }

    public String getOfficeName() {
        return "Kraków";
    }

}
