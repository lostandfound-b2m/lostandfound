package org.b2m.lostandfound;

import org.b2m.lostandfound.persist.LostItemDao;
import org.b2m.lostandfound.persist.LostPropertyDao;
import org.b2m.lostandfound.persist.LostPropertyOffice;
import org.b2m.lostandfound.persist.SourceFileDao;

import java.io.IOException;
import java.util.*;

public class DaoService {

    private static LostPropertyDao lostPropertyDao;


    public DaoService() {

        lostPropertyDao = new LostPropertyDao();

    }

    Date dateOfFound4 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
    LostItemDao myItem = new LostItemDao("walizka", dateOfFound4, dateOfFound4, "00-753", "Warszawa", "Warszawa");

    List<LostItemDao> rewriteItemData(LostPropertyOffice lostPropertyOffice) throws IOException {

        //SourceFileDao sourceFileDao = rewriteSourceFileData(sourceFile,lostPropertyOffice);
        List<Item> itemsFromParser = ItemListGetter.simpleGetListKrk();
        List<LostItemDao> itemsDao = new ArrayList<>();
        for (Item itemFromParser : itemsFromParser) {
            LostItemDao newLostItemDao = new LostItemDao(itemFromParser.getName(), itemFromParser.getFoundDate(), itemFromParser.getReceiveDate(), itemFromParser.getCityCode(), itemFromParser.getFoundPlace(), itemFromParser.getCityName());
            itemsDao.add(newLostItemDao);
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

    void addItems(List<LostItemDao> itemList) {
        lostPropertyDao.openCurrentSessionwithTransaction();
        for (LostItemDao newItem : itemList) {
            lostPropertyDao.persist(newItem);
        }
        lostPropertyDao.closeCurrentSessionwithTransaction();


    }

    void deleteItem(Item item) {

    }
    void deleteItems(List<Item> itemList) {
        for (Item item : itemList) {
            deleteItem(item);
        }
    }

    public List<LostItemDao> findItems(String description, String cityName) {
        lostPropertyDao.openCurrentSessionwithTransaction();
        List<LostItemDao> itemsDao = lostPropertyDao.findByItemDescription(description, cityName);
        lostPropertyDao.closeCurrentSessionwithTransaction();
        return itemsDao;
    }
}
