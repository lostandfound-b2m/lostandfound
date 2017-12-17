package org.b2m.lostandfound;

import java.util.ArrayList;
import java.util.List;

public class DaoService {
    /* zwraca wszystkie obiekty SourceFile powiązane z danym biurem */
    List<SourceFile> getSourceFiles(String officeName) {
        return null;
    }

    void addSourceFiles(SourceFile sourceFile) {}
    void addSourceFiles(List<SourceFile> sourceFileList) {
        for (SourceFile file : sourceFileList) {
            addSourceFiles(file);
        }
    }

    /* usuwa z bazy danych aktualnie znajdujące się tam SourceFile */
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

    void addItems(List<Item> itemList) {}

    void deleteItem(Item item) {}
    void deleteItems(List<Item> itemList) {
        for (Item item : itemList) {
            deleteItem(item);
        }
    }
}
