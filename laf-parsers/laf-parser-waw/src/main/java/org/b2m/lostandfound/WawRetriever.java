package org.b2m.lostandfound;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WawRetriever implements Retriever {
    public List<Item> retrieveItemsFromFiles(List<SourceFile> files) throws IOException {
        List<Item> items = new ArrayList<>();
        for (SourceFile file : files) {
            System.out.println("WawRetriever works");
            try {
                items.addAll(new ParserWarsaw(file.getUrl()).getLostItemsFromParser());
            }
            catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    public List<SourceFile> retrieveFiles() {
        String url = "https://bip.warszawa.pl/Menu_przedmiotowe/ogloszenia/rzeczy_znalezione/default.htm";
        List<SourceFile> resultList = new ArrayList<>();
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        }
        catch (IOException e) {
            return null;
        }
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
