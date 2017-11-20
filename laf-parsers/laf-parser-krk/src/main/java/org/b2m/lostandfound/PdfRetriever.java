package org.b2m.lostandfound;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Objects;

/*
 * Class that finds URL to PDF file on Krakow L&F Office resources site,
 * such as this one: https://www.bip.krakow.pl/zalaczniki/dokumenty/n/189876/karta,
 * and retrieves all attributes of PdfFile object.
 *
 */
public class PdfRetriever {

    private static Logger log = Logger.getLogger(PdfRetriever.class);

    public static void retrieve(String url, PdfFile fileToBeFound) throws IOException, ParseException {
        log.info("Retrieving HTML");
        Document doc=Jsoup.connect(url).get();
        Element content = doc.getElementById("mainDiv");

        /* Attempt to find URL */
        Elements urls = content.getElementsByTag("a");
        for (Element urlToBeFound : urls)
            if (Objects.equals(urlToBeFound.text(), "zobacz"))
                fileToBeFound.setUrl(urlToBeFound.attr("abs:href"));

        /* Attempt to find last update's date */
        Elements infos = content.getElementsByClass("metka_td3");
        int nr = 0;
        for (Element info : infos) {
            if (nr==5) {
                fileToBeFound.setLastUpdate(StringToDate(infos.get(nr).text()));
            }
            ++nr;
        }
    }

    private static Date StringToDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(strDate);
    }
}
