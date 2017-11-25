package org.b2m.lostandfound;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ItemListGetter {


    public static void getList(KrkRetriever retriever) throws IOException{
        List<SourceFile> files = retriever.retrieve("https://www.bip.krakow.pl/?dok_id=19964");
        /*
         * Tutaj będziemy chcieli sprawdzić, czy jest sens aktualizować wszystkie pliki,
         * z DAO powinna do nas zaktualizowana List<SourceFile> z tylko tymi linkami,
         * które prowadzą do nowych/zaktualizowanych plików.
         */
        List<Item> urls = new ArrayList<Item>();
        for (SourceFile file : files) {
            urls.addAll(new ParserKrk(new URL(file.getUrl())).getItemList());
        }
        getJson(urls);
    }

    static void getJson(List<Item> items) throws IOException {
        Writer writer = new FileWriter("Output.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(items, writer);

        writer.close();
    }


}
