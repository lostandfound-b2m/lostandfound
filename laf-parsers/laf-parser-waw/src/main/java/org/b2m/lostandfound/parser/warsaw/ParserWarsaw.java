package org.b2m.lostandfound.parser.warsaw;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.b2m.lostandfound.Item;

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
    private String FILE_NAME = "C:\\Users\\Michał61\\Desktop\\ProjektEiti\\laf-parsers\\laf-parser-waw\\src\\main\\resources\\warsaw.xls";
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

    ;

    public ParserWarsaw(String url) throws IOException {
        inputStream = new URL(url).openStream();
    }

    boolean isCellNULL(Cell currentCell) {
        if (currentCell == null)
            return true;
        else
            return false;
    }

    public Date getFoundDateFromFile(Row currentRow) throws IOException, ParseException, NullPointerException {
        dateFoundCell = currentRow.getCell(1);
        if (isCellNULL(dateFoundCell))
            throw new NullPointerException("Pusta komorka");

        return returnDateFromString(dateFoundCell.toString());


    }

    public Date getDateReceivedFromFile(Row currentRow) throws IOException, ParseException {
        dateReceivedCell = currentRow.getCell(2);
        if (isCellNULL(dateReceivedCell))
            throw new NullPointerException("Pusta komorka");

        return returnDateFromString(dateReceivedCell.toString());

    }

    public String getItemDescriptionFromFile(Row currentRow) throws IOException {
        itemDescriptionCell = currentRow.getCell(3);
        if (isCellNULL(itemDescriptionCell))
            new NullPointerException("Pusta komorka");

        return itemDescriptionCell.toString();

    }

    public String getFoundPlaceFromFile(Row currentRow) throws IOException {
        placeFoundCell = currentRow.getCell(4);
        if (isCellNULL(placeFoundCell))
            throw new NullPointerException("Pusta komorka");

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
            Item newItem = new Item(getItemDescriptionFromFile(currentRow), getFoundDateFromFile(currentRow), getDateReceivedFromFile(currentRow), "02-798", getFoundPlaceFromFile(currentRow), "Warszawa");
            itemsFromParser.add(newItem);
            System.out.println(newItem);
        }
        return itemsFromParser;
    }

}

