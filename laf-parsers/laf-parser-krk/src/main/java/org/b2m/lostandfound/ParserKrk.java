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

public class ParserKrk {
    private InputStream inputStream;

    public ParserKrk(URL url) throws IOException {
        inputStream = url.openStream();
    }

    public ParserKrk(String string) throws IOException {
        File file = new File(string);
        inputStream = new FileInputStream(file);
    }


    private String getFromFile(InputStream inputStream) throws IOException {
        String output;
        PDDocument document = PDDocument.load(inputStream);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        output = pdfTextStripper.getText(document);
        return output;
    }

    public List<Item> getItemList() throws IOException {
        return getAllItems(getFromFile(inputStream));
    }


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
            }
            if(itemName != null && cityCode!=null)
                allItems.add(new Item(itemName, findDate, cityCode, "Kraków"));
        } while (endOfLine != endOfText);
        return allItems;
    }

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
