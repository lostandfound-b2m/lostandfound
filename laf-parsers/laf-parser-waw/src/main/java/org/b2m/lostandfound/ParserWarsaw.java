package org.b2m.lostandfound;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ParserWarsaw {

    private InputStream inputStream;
    private String FILE_NAME = "src/main/resources/warsaw.xls";
    FileInputStream excelFile;
    HSSFWorkbook workbook;
    HSSFSheet worksheet;
    Cell dateFoundCell;
    Cell dateReceivedCell;
    Cell itemDescriptionCell;
    Cell placeFoundCell;


    public ParserWarsaw() throws IOException {
        excelFile = new FileInputStream(new File(FILE_NAME));
        workbook = new HSSFWorkbook(excelFile);
        worksheet = workbook.getSheetAt(0);
    }

    public ParserWarsaw(String url) throws IOException {
        inputStream = new URL(url).openStream();
        workbook = new HSSFWorkbook(inputStream);
        worksheet = workbook.getSheetAt(0);
    }

    public Date getFoundDateFromFile(Row currentRow) throws IOException, ParseException, NullPointerException {
        dateFoundCell = currentRow.getCell(1);
        return returnDateFromString(dateFoundCell.toString());
    }

    public Date getDateReceivedFromFile(Row currentRow) throws IOException, ParseException {
        dateReceivedCell = currentRow.getCell(2);
        return returnDateFromString(dateReceivedCell.toString());
    }

    public String getItemDescriptionFromFile(Row currentRow) throws IOException {
        itemDescriptionCell = currentRow.getCell(3);
        return itemDescriptionCell.toString();
    }

    public String getFoundPlaceFromFile(Row currentRow) throws IOException {
        placeFoundCell = currentRow.getCell(4);
        return placeFoundCell.toString();
    }

    public Date returnDateFromString(String dateString) throws ParseException {
        Date currentDate;
        DateFormat format = new SimpleDateFormat("dd-MMMM-yyyy", new Locale("pl", "PL"));
        try {
            currentDate = format.parse(dateString);
        } catch (ParseException e) {

            currentDate = null;
        }
        return currentDate;
    }

    public List<Item> getLostItemsFromParser() throws IOException, ParseException {
        List<Item> itemsFromParser = new ArrayList<>();
        for (Row currentRow : worksheet) {
            Item newItem = new Item();
            try {
                newItem = new Item(getItemDescriptionFromFile(currentRow), getFoundDateFromFile(currentRow), getDateReceivedFromFile(currentRow), "02-798", getFoundPlaceFromFile(currentRow), "");
            }
            catch (NullPointerException e) {
            }
            if (newItem.getName() != null) {
                itemsFromParser.add(newItem);
            }
        }
        //usuwanie opisow
        itemsFromParser.remove(0);
        itemsFromParser.remove(0);

        return itemsFromParser;
    }

}

