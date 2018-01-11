package org.b2m.lostandfound;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@Component
public class Service {

    private static LostPropertyRepository lostPropertyRepository;


    Service() {
        lostPropertyRepository = new LostPropertyDao();
    }

    //Date dateOfFound4 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
    //ItemInRepository myItem = new ItemInRepository("walizka", dateOfFound4, dateOfFound4, "00-753", "Warszawa", "Warszawa");

    /* Funkcje przepisujace obiekty z laf-parsers do obiektow w laf-persist */
    private ItemInRepository createNewItemDaoFromItem(Item itemFromParser){
        ItemInRepository newItemInRepository = new ItemInRepository(itemFromParser.getName(), itemFromParser.getFoundDate(), itemFromParser.getReceiveDate(), itemFromParser.getCityCode(), itemFromParser.getFoundPlace(), itemFromParser.getCityName(),findLostPropertyOffice(itemFromParser.getCityName()));
        return newItemInRepository;
    }

    private List<ItemInRepository> createNewItemDaoListFromItemList(List<Item> items) {
        List<ItemInRepository> itemInRepositoryList = new ArrayList<>();
        for (Item item : items) {
            itemInRepositoryList.add(createNewItemDaoFromItem(item));
        }
        return itemInRepositoryList;
    }


    private SourceFileInRepository createNewSourceFileDaoFromSourceFile(SourceFile file) {
        SourceFileInRepository fileDao = new SourceFileInRepository(file.getName(),file.getOfficeName(),file.getFileDate(),file.getUpdateChecker(),file.getUrl());
        return fileDao;
    }

    private List<SourceFileInRepository> createNewSourceFileDaoListFromSourceFileList(List<SourceFile> files) {
        List<SourceFileInRepository> fileDaoList = new ArrayList<>();
        for (SourceFile file : files) {
            fileDaoList.add(createNewSourceFileDaoFromSourceFile(file));
        }
        return fileDaoList;
    }
    //temporarily without source file
    private List<ItemInRepository> getRewritedItemsDaoFromParsedItems(LostPropertyOffice lostPropertyOffice, List<Item> items) throws IOException {

        //SourceFileInRepository sourceFileDao = rewriteSourceFileData(sourceFile,lostPropertyOffice);
        List<Item> itemsFromParser = items;
        List<ItemInRepository> itemsDao = new ArrayList<>();
        for (Item itemFromParser : itemsFromParser) {
            itemsDao.add(createNewItemDaoFromItem(itemFromParser));
        }
        return itemsDao;
    }

    private SourceFileInRepository rewriteSourceFileData(SourceFile sourceFile, String lostPropertyOffice) {
        SourceFileInRepository sourceFileInRepository = new SourceFileInRepository(sourceFile.getName(), lostPropertyOffice, sourceFile.getFileDate(), sourceFile.getUpdateChecker(), sourceFile.getUrl());
        return sourceFileInRepository;
    }
    List<SourceFile> getSourceFiles(String officeName) {
        //return  lostPropertyRepository.getSourceFile(officeName);
        return null;
    }

    void addSourceFiles(SourceFile sourceFile) {
        //return  lostPropertyRepository.addSourceFile(createNewSourceFileDaoFromSourceFile(sourceFile));
    }

    void addSourceFiles(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            addSourceFiles(file);
        }
    }


    void deleteSourceFile(SourceFile sourceFile) {
        //return  lostPropertyRepository.deleteSourceFile(createNewSourceFileDaoFromSourceFile(sourceFile));
    }
    void deleteSourceFile(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            deleteSourceFile(file);
        }
    }

    List<Item> getItemsListedOnSourceFile(SourceFile sourceFile) {
        //return  lostPropertyRepository.getItemsListedOnSourceFile(createNewSourceFileDaoFromSourceFile(sourceFile));
        return null;
    }

    List<Item> getItemsListedOnSourceFile(List<SourceFile> sourceFileList) {
        List<Item> items = new ArrayList<>();
        for (SourceFile file : sourceFileList) {
            items.addAll(getItemsListedOnSourceFile(file));
        }
        return items;
    }

    void deleteItemsListedOnSourceFile(SourceFile sourceFile) {
        //return  lostPropertyRepository.deleteItemsListedOnSourceFile(createNewSourceFileDaoFromSourceFile(sourceFile));
    }

    void deleteItemsListedOnSourceFile(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            deleteItemsListedOnSourceFile(file);
        }
    }
    void addItems(List<Item> itemList) {
        addItemsDao(createNewItemDaoListFromItemList(itemList));
    }
    void addItemsDao(List<ItemInRepository> itemList) {
        lostPropertyRepository.addLostItems(itemList);
    }

    void addLostPropertyOffice(LostPropertyOffice office){
        lostPropertyRepository.addLostPropertyOffice(office);
    }

    void deleteItems(List<Item> itemList) {
        deleteItemsDao(createNewItemDaoListFromItemList(itemList));
    }

    void deleteItemsDao(List<ItemInRepository> itemList) {
        lostPropertyRepository.deleteLostItems(itemList);
    }

    void deleteLostPropertyOffice(String officeName){
        lostPropertyRepository.deleteLostPropertyOffice(officeName);
    }

    public List<ItemInRepository> findByItemDescription(String itemDescription, String cityName) {
        return lostPropertyRepository.findByItemDescription(itemDescription,cityName);
    }

    List<ItemInRepository> returnAllItemsFromOffice(String office){
        return  lostPropertyRepository.returnAllItemsFromOffice(office);
    }

    List<ItemInRepository> returnAllItems(){
        return  lostPropertyRepository.returnAllItems();
    }

    List<ItemInRepository> findItems(String itemDescription, String cityName) {
        return lostPropertyRepository.findByItemDescription(itemDescription,cityName);
    }
    LostPropertyOffice findLostPropertyOffice(String cityName){
        return lostPropertyRepository.findLostPropertyOffice(cityName);
    }


    /* A series of simpleGetList- functions that return List<Item> of all items
     * found on given office's website or write this List to Json file
     *
     * NIE TYKAC MI TEGO!!!!!!! potem pewnie trzeba bedzie wywalic bo to bedzie gdzie indziej
     * ale na razie zostawic
     */
    public void simpleGetListAndSaveInJson(Retriever retriever) throws IOException {
        List<SourceFile> files = retriever.retrieveFiles();
        getJson(retriever.retrieveItemsFromFiles(files));
    }

    public List<Item> simpleGetList(Retriever retriever) throws IOException {
        List<SourceFile> files = retriever.retrieveFiles();
        return retriever.retrieveItemsFromFiles(files);
    }

    public List<Item> simpleGetListKrk() throws IOException {
        KrkRetriever retriever = new KrkRetriever();
        return simpleGetList(retriever);
    }

    public List<Item> simpleGetListGd() throws IOException {
        GdRetriever retriever = new GdRetriever();
        return simpleGetList(retriever);
    }

    private void getJson(List<Item> items) throws IOException {
        Writer writer = new FileWriter("Output.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(items, writer);

        writer.close();
    }
}
