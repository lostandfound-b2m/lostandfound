package org.b2m.lostandfound.parser.warsaw;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ParserWarsaw {

    private InputStream inputStream;
    private String FILE_NAME = "C:\\Users\\Michał61\\Desktop\\ProjektEiti\\laf-parsers\\laf-parser-waw\\src\\main\\resources\\warsaw.xls";


    public ParserWarsaw() {
    }

    ;

    public ParserWarsaw(String url) throws IOException {
        inputStream = new URL(url).openStream();
    }

    public void getData() throws IOException {

        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));

        Workbook workbook = new HSSFWorkbook(excelFile);


    }
}
