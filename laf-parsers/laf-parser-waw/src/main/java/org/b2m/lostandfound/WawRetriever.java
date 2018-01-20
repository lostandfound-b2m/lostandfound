package org.b2m.lostandfound;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WawRetriever implements Retriever {
    public List<Item> retrieveItemsFromFiles(List<SourceFile> files) {
        List<Item> items = new ArrayList<>();
        if (files == null) {
            System.out.println("Pliki nie zostaly pobrane");
            return items;
        }
        for (SourceFile file : files) {
            List<Item> itemsFromFile = new ArrayList<>();
            try {
                itemsFromFile = new ParserWarsaw(file.getUrl()).getLostItemsFromParser();
            } catch (IOException e) {return null;};
            for (Item item : itemsFromFile) {
                item.setFile(file);
            }
            items.addAll(itemsFromFile);
        }
        return items;
    }

    public List<SourceFile> retrieveFiles() throws IOException {
        String url = "https://bip.warszawa.pl/Menu_przedmiotowe/ogloszenia/rzeczy_znalezione/default.htm";
        List<SourceFile> resultList = new ArrayList<>();
        Document doc;
        doc = Jsoup.connect(url).get();

        Element content = doc.getElementById("plc_Htmlplaceholdercontrol2");

        /* Looking for URLs with regex */
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            if (link.text().matches("Wykaz.*")) {
                SourceFile file = new SourceFile();
                file.setName(link.text());
                file.setUrl(link.attr("abs:href"));
                file.setUpdateChecker(Integer.toString(content.hashCode()));
                file.setOfficeName("Warszawa");
                resultList.add(file);
            }
        }
        return resultList;
    }

    public String getOfficeName() {
        return "Warszawa";
    }

}
