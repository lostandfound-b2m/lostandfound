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
        ItemInRepository newItemInRepository = new ItemInRepository(itemFromParser.getName(),
                itemFromParser.getFoundDate(), itemFromParser.getReceiveDate(), itemFromParser.getCityCode(),
                itemFromParser.getFoundPlace(), itemFromParser.getCityName(),
                findLostPropertyOffice(itemFromParser.getOfficeName()),
                createNewSourceFileDaoFromSourceFile(itemFromParser.getFile()));
        return newItemInRepository;
    }

    private List<ItemInRepository> createNewItemDaoListFromItemList(List<Item> items) {
        List<ItemInRepository> itemInRepositoryList = new ArrayList<>();
        for (Item item : items) {
            itemInRepositoryList.add(createNewItemDaoFromItem(item));
        }
        return itemInRepositoryList;
    }

    private Item createNewItemFromItemDao(ItemInRepository itemInRepository){
        System.out.println(itemInRepository.getFile());
        SourceFile file = createNewSourceFileFromSourceFileDao(itemInRepository.getSourceFile());
        Item item = new Item(itemInRepository.getName(), itemInRepository.getFindDate(),
                itemInRepository.getReceiveDate(), itemInRepository.getCityCode(), itemInRepository.getFindPlace(),
                itemInRepository.getLostPropertyOffice().getOfficeName(),
                file);
        return item;
    }

    private List<Item> createNewItemListFromItemListDao(List<ItemInRepository> itemsInRepository) {
        List<Item> items = new ArrayList<>();
        for (ItemInRepository item : itemsInRepository) {
            items.add(createNewItemFromItemDao(item));
        }
        return items;
    }


    private SourceFileInRepository createNewSourceFileDaoFromSourceFile(SourceFile file) {
        LostPropertyOffice office = lostPropertyRepository.findLostPropertyOffice(file.getOfficeName());
        SourceFileInRepository fileDao = new SourceFileInRepository(file.getName(),office,file.getUpdateChecker(),file.getUrl());
        return fileDao;
    }

    private SourceFile createNewSourceFileFromSourceFileDao(SourceFileInRepository file) {
        return new SourceFile(file.getUrl(),file.getName(),file.getUpdateChecker(),file.getOffice().getOfficeName());
    }

    private List<SourceFileInRepository> createNewSourceFileDaoListFromSourceFileList(List<SourceFile> files) {
        List<SourceFileInRepository> fileDaoList = new ArrayList<>();
        for (SourceFile file : files) {
            fileDaoList.add(createNewSourceFileDaoFromSourceFile(file));
        }
        return fileDaoList;
    }

    private List<SourceFile> createNewSourceFileListFromSourceFileDaoList(List<SourceFileInRepository> files) {
        List<SourceFile> sourceFiles = new ArrayList<>();
        for (SourceFileInRepository file : files) {
            sourceFiles.add(createNewSourceFileFromSourceFileDao(file));
        }
        return sourceFiles;
    }

    List<SourceFile> getSourceFiles(String officeName) {
        return  createNewSourceFileListFromSourceFileDaoList(lostPropertyRepository.getSourceFiles(officeName));
    }

    void addSourceFiles(SourceFile sourceFile) {
        lostPropertyRepository.addSourceFile(createNewSourceFileDaoFromSourceFile(sourceFile));
    }

    void addSourceFiles(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            addSourceFiles(file);
        }
    }


    void deleteSourceFile(SourceFile sourceFile) {
        lostPropertyRepository.deleteSourceFile(createNewSourceFileDaoFromSourceFile(sourceFile));
    }

    void deleteSourceFile(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            deleteSourceFile(file);
        }
    }

    List<Item> getItemsListedOnSourceFile(SourceFile sourceFile) {
        return createNewItemListFromItemListDao(lostPropertyRepository.getItemsListedOnSourceFile(createNewSourceFileDaoFromSourceFile(sourceFile)));
    }

    List<Item> getItemsListedOnSourceFile(List<SourceFile> sourceFileList) {
        List<Item> items = new ArrayList<>();
        for (SourceFile file : sourceFileList) {
            items.addAll(getItemsListedOnSourceFile(file));
        }
        return items;
    }

    void deleteItemsListedOnSourceFile(SourceFile sourceFile) {
        lostPropertyRepository.deleteItemsListedOnSourceFile(createNewSourceFileDaoFromSourceFile(sourceFile));
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
