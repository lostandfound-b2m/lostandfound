package org.b2m.lostandfound;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ItemListGetter {


    public static void getList(Retriever retriever) throws IOException{
        DaoService daoService = new DaoService();
        List<SourceFile> files = retriever.retrieveFiles();
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

        /*
         * SourceFileDao objects are divided into few lists:
         * 1. newFiles : list of SourceFiles that appeared on office's site for the first time
         * 2. filesToBeUpdated and FileInDatabaseToBeUpdated :
         *      lists of SourceFiles that has been updated since last time
         * 3. filesToBeDeletedFromDatabase : list of files that are not listed on the
         *      office's site anymore
         */

        /* SourceFileDao was removed from office's site or updated */
        for (SourceFile fileInDatabase : filesInDatabase) {
            isFileStillAvailable = false;
            for (SourceFile file : files){
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

        /* SourceFileDao is listed on office's site but we don't have it in database yet */
        for (SourceFile file : files) {
            if (!filesInDatabase.contains(file)) {
                newFiles.add(file);
            }
        }

        /*
         * Taking care of updated files: we get lists of Items from both
         * new and old version of file and compare them.
         * Items that haven't changed are ignored, Items that have been removed
         * are added to itemsToBeDeleted list, new Items to itemsToAdd list.
         */

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

        /* Items from new files are added ti itemsToAdd list */
        itemsToAdd.addAll(retriever.retrieveItemsFromFiles(newFiles));

        /* Unnecessary Items and SourceFiles removed */
        daoService.deleteItemsListedOnSourceFile(filesToBeDeletedFromDatabase);
        daoService.deleteItems(itemsToBeDeleted);
        daoService.deleteSourceFile(filesToBeDeletedFromDatabase);

        /* New Items and new/modified SourceFiles added */
        daoService.addSourceFiles(newFiles);
        daoService.addItems(itemsToAdd);
        getJson(retriever.retrieveItemsFromFiles(files));
    }

    public static void simpleGetList(Retriever retriever) throws IOException {
        List<SourceFile> files = retriever.retrieveFiles();
        getJson(retriever.retrieveItemsFromFiles(files));
    }

    private static void getJson(List<Item> items) throws IOException {
        Writer writer = new FileWriter("Output.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(items, writer);

        writer.close();
    }


}
