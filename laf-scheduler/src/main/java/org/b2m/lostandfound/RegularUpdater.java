package org.b2m.lostandfound;

import javax.xml.transform.Source;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa jest odpowiedzialna za analizę różnic między plikami SourceFile przechowywanymi
 * w bazie danych, a plikami znajdującymi się aktualnie na stronie biura.
 */
public class RegularUpdater {
    Retriever retriever;
    Service daoService;
    RegularUpdater(Retriever retriever, Service daoService){
        this.retriever=retriever;
        this.daoService=daoService;
    }

    /**
     * Umożliwia pobranie i zapis do bazy danych informacji o znalezionych przedmiotach umieszczonych
     * jedynie w nowych plikach, oraz usunięcie informacji o przedmiotach, które już
     * nie figurują na stronie biura.
     */
    void doScheduledUpdate() {
        System.out.println("---Scheduled update for " + retriever.getOfficeName() + " starts--- " );
        List<SourceFile> filesFromRetriever;
        try {
            filesFromRetriever = retriever.retrieveFiles();
        } catch (IOException e) {
            System.out.println("  Connection error, aborting update" );
            return;
        }
        List<SourceFile> filesInDatabase = daoService.getSourceFiles(retriever.getOfficeName());
        List<SourceFile> newFiles = new ArrayList<>();
        List<SourceFile> filesToBeUpdated = new ArrayList<>();
        List<SourceFile> filesInDatabaseToBeUpdated = new ArrayList<>();
        List<SourceFile> filesToBeDeletedFromDatabase = new ArrayList<>();
        List<Item> itemsToBeAnalized;
        List<Item> itemsInDatabaseToBeAnalized;
        List<Item> itemsToAdd = new ArrayList<>();
        List<Item> itemsToBeDeleted = new ArrayList<>();
        boolean isFileStillAvailable;

        //SourceFileInRepository was removed from office's site or updated
        for (SourceFile fileInDatabase : filesInDatabase) {
            isFileStillAvailable = false;
            for (SourceFile file : filesFromRetriever){
                if (file.getName().equals(fileInDatabase.getName())) {
                    if (!file.getUpdateChecker().equals(fileInDatabase.getUpdateChecker())) {
                        filesInDatabaseToBeUpdated.add(fileInDatabase);
                        filesToBeUpdated.add(file);
                    }
                    else {
                        isFileStillAvailable = true;
                    }
                    break;
                }
            }
            if(!isFileStillAvailable) {
                filesToBeDeletedFromDatabase.add(fileInDatabase);
            }
        }
        //SourceFileInRepository is listed on office's site but we don't have it in database yet
        for (SourceFile file : filesFromRetriever) {
            boolean isNew = true;
            for (SourceFile fileInDatabase : filesInDatabase) {
                if (file.getName().equals(fileInDatabase.getName())) {
                    isNew=false;
                    break;
                }
            }
            if (isNew) {
                newFiles.add(file);
            }
        }
        try {
            itemsToBeAnalized = retriever.retrieveItemsFromFiles(filesToBeUpdated);
        } catch (IOException e) {
            System.out.println("  Connection error, aborting update" );
            return;
        }
        itemsInDatabaseToBeAnalized = daoService.getItemsListedOnSourceFile(filesInDatabaseToBeUpdated);

        for (Item item : itemsToBeAnalized) {
            if (!itemsInDatabaseToBeAnalized.contains(item)) {
                itemsToAdd.add(item);
            }
            else {
                itemsInDatabaseToBeAnalized.remove(item);
            }
        }
        itemsToBeDeleted.addAll(itemsInDatabaseToBeAnalized);
        // Items from new files are added ti itemsToAdd list
        try {
            itemsToAdd.addAll(retriever.retrieveItemsFromFiles(newFiles));
        } catch (IOException e) {
            System.out.println("  Connection error, aborting update" );
            return;
        }
        System.out.println("  No. of deleted files: " + filesToBeDeletedFromDatabase.size());
        System.out.println("  No. of new files: " + newFiles.size());
        // Unnecessary Items and SourceFiles removed
        daoService.deleteItemsListedOnSourceFile(filesToBeDeletedFromDatabase);
        daoService.deleteItems(itemsToBeDeleted);
        daoService.deleteSourceFile(filesToBeDeletedFromDatabase);
        // New Items and new/modified SourceFiles added
        daoService.addSourceFiles(newFiles);
        daoService.addItems(itemsToAdd);
        //getJson(retriever.retrieveItemsFromFiles(files));
    }
}
