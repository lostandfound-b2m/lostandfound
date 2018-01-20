package org.b2m.lostandfound;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GdRetriever implements Retriever{
    public List<Item> retrieveItemsFromFiles(List<SourceFile> files) throws IOException {
        List<Item> items = new ArrayList<>();
        for (SourceFile file : files) {
            List<Item> itemsFromFile = new ParserGd(new URL(file.getUrl())).getItemList();
            for (Item item : itemsFromFile) {
                item.setFile(file);
            }
            items.addAll(itemsFromFile);
        }
        return items;
    }

    public List<SourceFile> retrieveFiles() throws  IOException {
        String url = "http://bip.gdansk.pl/urzad-miejski/Informacje-biura-rzeczy-znalezionych,a,50360";
        List<SourceFile> resultList = new ArrayList<>();
        SourceFile file = new SourceFile();
        file.setUrl(url);
        file.setName("Gdańsk");
        file.setOfficeName("Gdańsk");
        Document doc = null;
        /* updateChecker in this case is a hashCode of the content of the office's site */
        doc = Jsoup.connect(url).get();
        Elements content = doc.getElementsByClass("article");
        file.setUpdateChecker(Integer.toString(content.hashCode()));

        resultList.add(file);
        return resultList;
    }

    @Override
    public String getOfficeName() {
        return "Gdańsk";
    }
}
