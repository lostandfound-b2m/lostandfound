package org.b2m.lostandfound;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class PdfRetriever {

    private static Logger log = Logger.getLogger(PdfRetriever.class);

    public PdfFile retrieve(String url) throws IOException {
        log.info("Retrieving HTML");
        Document doc=Jsoup.connect(url).get();
        System.out.println(doc.title());

        return null;
    }


}
