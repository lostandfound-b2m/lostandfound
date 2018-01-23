package org.b2m.lostandfound;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ta klasa wykorzystuje framework Apache PDFBox który pozwana na pobranie treści zawartej w pliku formatu PDF.
 */
public class ParserKrk {

    private InputStream inputStream;

    /**
     * Ten konstrukor służy do dynamicznego pobierania zawartości dokumentu PDF czyli bezpośrednio z link url
     * @param url Obiekt klasy URL który przekazuje link pod którym znajduje się zapisany na stronie dokument
     * @throws IOException Wyjątek wyrzucany w przypadku gdy nie uda się otworzyć strumienia do input stream.
     */
    public ParserKrk(URL url) throws IOException {
        inputStream = url.openStream();
    }

    /**
     * Ten konstruktor jest jedynie wykorzystywany do statycznego pobierania danych w testach w celu weryfikacji poprawności działania parsera
     * @param string Przekazana jest tutaj ścieżka do pliku
     * @throws IOException Wyjątek wyrzucany w przypadku gdy nie uda się otworzyć strumienia do input stream.
     */
    public ParserKrk(String string) throws IOException {
        File file = new File(string);
        inputStream = new FileInputStream(file);
    }

    /**
     * Jest to prywatna metoda która korzystając z PDFBox pobiera cały tekst zawarty w dokumencie do dalszego przetwarzania
     * @param inputStream Przekazywany jest przez ten parametr strumień wejściowy
     * @return Zwracany jest String zawierający całość tekstu zawartego w pliku PDF
     * @throws IOException Wyjątek jest wyrzucany w przypadku gdy nie udało się pobrać tekstu
     */
    private String getFromFile(InputStream inputStream) throws IOException {
        String output;
        PDDocument document = PDDocument.load(inputStream);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        output = pdfTextStripper.getText(document);
        inputStream.close();
        return output;
    }

    /**
     * Jest to jedyna publiczna metoda w klasie która służy do wywołania prywatnej metody która wyciąga potrzebne dane z całego jednego Stringa
     * @return Lista przedmiotów uzyskana z całej zwartości dokumentu
     * @throws IOException Wyjatek wyrzucany gdy wyrzuca go metoda getAllItems
     */
    public List<Item> getItemList() throws IOException {
        return getAllItems(getFromFile(inputStream));
    }

    /**
     * Ta metoda przegląda cały tekst pobrany z pliku i wybiera dane potrzebne do utworzenia listy przedmiotów
     * @param inputPDF Jest to String który zawiera cały tekst z dokumentu
     * @return Lista wszystkich przedmiotów
     */
    private List<Item> getAllItems(String inputPDF) {
        List<Item> allItems = new LinkedList<>();
        int i = 0, beginOfLine = 0, endOfLine = 0, endOfText = 0, endOfTemp = 0;
        String temp;
        String itemName = null, cityCode = null;
        Date findDate = null;
        Item tempItem;
        Pattern pattern_cityCode = Pattern.compile("SA[[.]-]03\\.5314(\\.[0-9]+){1,2}\\.20[0-9]{2}");
        Pattern pattern_foundDate1 = Pattern.compile("[0-9]{0,2}-*[0-9]{2}-20[0-9]{2}");
        Pattern pattern_foundDate2 = Pattern.compile("[0-9]{0,2}\\.*[0-9]{2}\\.20[0-9]{2}");
        Matcher matcher;
        endOfText = inputPDF.lastIndexOf("\n");
        do {
            findDate = null;
            itemName = null;
            cityCode = null;
            beginOfLine = inputPDF.indexOf("\n", endOfLine);
            beginOfLine++;
            endOfLine = inputPDF.indexOf("\n", beginOfLine);
            temp = inputPDF.substring(beginOfLine, endOfLine);
            if (Character.isDigit(temp.charAt(0))) {
                temp = temp.substring(temp.indexOf(" ") + 1);
                matcher = pattern_cityCode.matcher(temp);
                if (matcher.find()) {
                    endOfTemp = matcher.end();
                    cityCode = temp.substring(0, endOfTemp++);
                }
                temp = temp.substring(endOfTemp,temp.length());
                matcher = pattern_foundDate1.matcher(temp);
                //System.out.println("temp1: "+ temp);
                if (matcher.find()) {
                    temp = temp.replace("-",".");
                    //System.out.println("temp2: "+ temp);
                    itemName = temp.substring(0, matcher.start() );
                    findDate = getDate(temp.substring(matcher.start(), matcher.end()));
                }
                matcher = pattern_foundDate2.matcher(temp);
                if (matcher.find()) {
                    itemName = temp.substring(0, matcher.start() );
                    findDate = getDate(temp.substring(matcher.start(), matcher.end()));
                }
                try {
                    itemName = itemName.trim();
                }
                catch (NullPointerException e)
                {}
            }
            if(itemName != null && cityCode!=null)
                allItems.add(new Item(itemName, findDate, cityCode, "Kraków"));
        } while (endOfLine != endOfText);
        return allItems;
    }

    /**
     * Ta metoda parsuje datę zapisaną w różnych formach do obiektu klasy Date
     * @param input Łańuch znaków zawierający datę
     * @return Obiekt klasy Date powstały po parsowaniu
     */
    private Date getDate(String input) {
        Date date;
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            date = df.parse(input);
        } catch (ParseException e) {
            DateFormat df2 = new SimpleDateFormat("MM.yyyy");
            try {
                date = df2.parse(input);
            } catch (ParseException e1) {
                date = null;
            }
        }
        return date;
    }

}
