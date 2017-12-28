package org.b2m.lostandfound;

import org.b2m.lostandfound.persist.*;
import java.io.IOException;
import java.util.*;

public class DaoService {

    private static LostPropertyRepository lostPropertyRepository;


    public DaoService() {
        lostPropertyRepository = new LostPropertyDao();
    }

    //Date dateOfFound4 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
    //ItemDao myItem = new ItemDao("walizka", dateOfFound4, dateOfFound4, "00-753", "Warszawa", "Warszawa");
    public ItemDao createNewItemDaoFromItem(Item itemFromParser){
        ItemDao newItemDao = new ItemDao(itemFromParser.getName(), itemFromParser.getFoundDate(), itemFromParser.getReceiveDate(), itemFromParser.getCityCode(), itemFromParser.getFoundPlace(), itemFromParser.getCityName());
        return newItemDao;
    }
    //temporarily without source file
    List<ItemDao> getRewritedItemsDaoFromParsedItems(LostPropertyOffice lostPropertyOffice,List<Item> items) throws IOException {

        //SourceFileDao sourceFileDao = rewriteSourceFileData(sourceFile,lostPropertyOffice);
        List<Item> itemsFromParser = items;
        List<ItemDao> itemsDao = new ArrayList<>();
        for (Item itemFromParser : itemsFromParser) {
            itemsDao.add(createNewItemDaoFromItem(itemFromParser));
        }
        return itemsDao;
    }

    SourceFileDao rewriteSourceFileData(SourceFile sourceFile, LostPropertyOffice lostPropertyOffice) {
        SourceFileDao sourceFileDao = new SourceFileDao(sourceFile.getName(), lostPropertyOffice, sourceFile.getFileDate(), sourceFile.getUpdateChecker(), sourceFile.getUrl());
        return sourceFileDao;
    }
    List<SourceFile> getSourceFiles(String officeName) {
        return null;
    }


    void addSourceFiles(SourceFile sourceFile) {}

    void addSourceFiles(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            addSourceFiles(file);
        }
    }


    void deleteSourceFile(SourceFile sourceFile) {}

    void deleteSourceFile(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            deleteSourceFile(file);
        }
    }

    List<Item> getItemsListedOnSourceFile(SourceFile sourceFile) {
        return null;
    }

    List<Item> getItemsListedOnSourceFile(List<SourceFile> sourceFileList) {
        List<Item> items = new ArrayList<>();
        for (SourceFile file : sourceFileList) {
            items.addAll(getItemsListedOnSourceFile(file));
        }
        return items;
    }

    void deleteItemsListedOnSourceFile(SourceFile sourceFile) {}

    void deleteItemsListedOnSourceFile(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            deleteItemsListedOnSourceFile(file);
        }
    }

    void addItems(List<ItemDao> itemList) {
        lostPropertyRepository.addLostItems(itemList);
    }

    void addLostPropertyOffice(LostPropertyOffice office){
        lostPropertyRepository.addLostPropertyOffice(office);
    }

    void deleteItems(List<ItemDao> itemList) {
        lostPropertyRepository.deleteLostItems(itemList);
    }

    void deleteLostPropertyOffice(LostPropertyOffice officeName){
        lostPropertyRepository.deleteLostPropertyOffice(officeName);
    }

    public List<ItemDao> findByItemDescription(String itemDescription, String cityName) {
        return lostPropertyRepository.findByItemDescription(itemDescription,cityName);
    }

    List<ItemDao> returnAllItemsFromOffice(LostPropertyOffice office){
        return  lostPropertyRepository.returnAllItemsFromOffice(office);
    }
}
