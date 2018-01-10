package org.b2m.lostandfound;

import javax.xml.transform.Source;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RegularUpdater {
    Retriever retriever;
    Service daoService;
    RegularUpdater(Retriever retriever, Service daoService){
        this.retriever=retriever;
        this.daoService=daoService;
    }
    void doScheduledUpdate() throws IOException{
        List<SourceFile> filesFromRetriever = retriever.retrieveFiles();
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
                }
            }
            if(!isFileStillAvailable) {
                filesToBeDeletedFromDatabase.add(fileInDatabase);
            }
        }

        //SourceFileInRepository is listed on office's site but we don't have it in database yet
        for (SourceFile file : filesFromRetriever) {
            if (!filesInDatabase.contains(file)) {
                newFiles.add(file);
            }
        }


        itemsToBeAnalized = retriever.retrieveItemsFromFiles(filesToBeUpdated);
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
        itemsToAdd.addAll(retriever.retrieveItemsFromFiles(newFiles));

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
