package org.b2m.lostandfound;

import org.b2m.lostandfound.persist.ItemDao;
import org.b2m.lostandfound.persist.LostPropertyDao;
import org.b2m.lostandfound.persist.LostPropertyOffice;
import org.b2m.lostandfound.persist.SourceFileDao;

import java.util.ArrayList;
import java.util.List;

public class DaoService {

    private static LostPropertyDao lostPropertyDao;
    private static ItemListGetter itemListGetter;

    public DaoService() {

        lostPropertyDao = new LostPropertyDao();
        itemListGetter = new ItemListGetter();
    }


    List<ItemDao> rewriteItemData(LostPropertyOffice lostPropertyOffice) {

        //SourceFileDao sourceFileDao = rewriteSourceFileData(sourceFile,lostPropertyOffice);
        List<Item> itemsFromParser = null;
        List<ItemDao> itemsDao = null;
        for (Item itemFromParser : itemsFromParser) {
            ItemDao newItemDao = new ItemDao(itemFromParser.getName(), itemFromParser.getFoundDate(), itemFromParser.getReceiveDate(), itemFromParser.getCityCode(), itemFromParser.getFoundPlace(), itemFromParser.getCityName());
            itemsDao.add(newItemDao);
        }
        return itemsDao;
    }

    SourceFileDao rewriteSourceFileData(SourceFile sourceFile, LostPropertyOffice lostPropertyOffice) {
        SourceFileDao sourceFileDao = new SourceFileDao(sourceFile.getName(), lostPropertyOffice, sourceFile.getFileDate(), sourceFile.getUpdateChecker(), sourceFile.getUrl());
        return sourceFileDao;
    }

    /* zwraca wszystkie obiekty SourceFileDao powiązane z danym biurem */
    List<SourceFile> getSourceFiles(String officeName) {
        return null;
    }


    void addSourceFiles(SourceFile sourceFile) {}
    void addSourceFiles(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            addSourceFiles(file);
        }
    }

    /* usuwa z bazy danych aktualnie znajdujące się tam SourceFileDao */
    void deleteSourceFile(SourceFile sourceFile) {}
    void deleteSourceFile(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            deleteSourceFile(file);
        }
    }

    /* zwraca listę Itemów powiązanych z podanym sourceFile */
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

    /* usuwa wszystkie Itemy powiązane z podanym sourceFile */
    void deleteItemsListedOnSourceFile(SourceFile sourceFile) {}
    void deleteItemsListedOnSourceFile(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            deleteItemsListedOnSourceFile(file);
        }
    }

    void addItems(List<Item> itemList) {

    }

    void deleteItem(Item item) {

    }
    void deleteItems(List<Item> itemList) {
        for (Item item : itemList) {
            deleteItem(item);
        }
    }

    public List<ItemDao> findItems(String description, String cityName) {
        lostPropertyDao.openCurrentSessionwithTransaction();
        List<ItemDao> itemsDao = lostPropertyDao.findByItemDescription(description, cityName);
        lostPropertyDao.closeCurrentSessionwithTransaction();
        return itemsDao;
    }
}
