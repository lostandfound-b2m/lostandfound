package org.b2m.lostandfound;

import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.net.URL;
import java.net.URLConnection;

public class ParserKrk implements ParserInterface {

    public ParserKrk() {
    }

    public String getFromURL(URL inputURL)throws IOException{
        String  output;
        InputStream input = inputURL.openStream();
        PDDocument pddDocument = PDDocument.load(input);
        PDFTextStripper textStripper = new PDFTextStripper();
        output = textStripper.getText(pddDocument);
        pddDocument.close();
        System.out.println(output);
        return output;
    }

    public String getFromFile(String pathToPDF) throws IOException{
        String  output;
        File file = new File(pathToPDF);
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        output = pdfTextStripper.getText(document);
        return output;
    }

    @Override
    public List<Item> getItemList() {

        return null;
    }

    @Override
    public List<Item> getItemListStaticSource() {
        int i = 0, j = 0, k = 0 ;
        String inputPDF = null;
        try {
            inputPDF = getFromFile("/home/luke/Pulpit/a.pdf");
            return getAllItems(inputPDF);
        }
        catch (IOException e)
        {
            return null;
        }
    }

    private List<Item> getAllItems(String inputPDF)
    {
        List<Item> allItems = new LinkedList<Item>();
        int i = 0 , beginOfLine = 0 , endOfLine = 0, endOfText = 0, endOfTemp = 0;
        String temp;
        String  itemName = null, cityCode = null;
        Date    findDate = null;
        Item tempItem;
        Pattern pattern;
        Matcher matcher;
        endOfText = inputPDF.lastIndexOf("\n");
        do {
            beginOfLine = inputPDF.indexOf("\n",endOfLine);
            beginOfLine++;
            endOfLine = inputPDF.indexOf("\n",beginOfLine);
            temp = inputPDF.substring(beginOfLine,endOfLine);
            if(Character.isDigit(temp.charAt(0))){
                temp = temp.substring (temp.indexOf (" ") + 1);
                pattern = Pattern.compile ("SA-03\\.5314\\.[0-9]+\\.[0-9]+\\.[0-9]+");
                matcher = pattern.matcher (temp);
                if (matcher.find ()) {
                    endOfTemp = matcher.end ();
                    cityCode = temp.substring (0, endOfTemp++);
                }

                pattern = Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{4}");
                matcher = pattern.matcher(temp);
                if(matcher.find()) {
                    temp.replaceAll("-", ".");
                    itemName = temp.substring(endOfTemp, matcher.start()-1);
                    findDate = getDate(temp.substring(matcher.start(), matcher.end()));
                }
                pattern = Pattern.compile("[0-9]{2}.[0-9]{2}.[0-9]{4}");
                matcher = pattern.matcher(temp);

                if(matcher.find()) {
                    itemName = temp.substring(endOfTemp, matcher.start()-1);
                    findDate = getDate(temp.substring(matcher.start(), matcher.end()));
                }

            }
            allItems.add(new Item(itemName,findDate,cityCode,"Kraków"));
        }while (endOfLine != endOfText);


        return allItems;
    }

    private Date getDate(String input) {
        Date date;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = df.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
            date=null;
        }
        return date;
    }

}
