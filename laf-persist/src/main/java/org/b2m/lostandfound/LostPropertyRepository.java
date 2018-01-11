package org.b2m.lostandfound;

import com.sun.org.apache.bcel.internal.classfile.SourceFile;

import java.util.List;

public interface LostPropertyRepository {

    void addLostItems(List<ItemInRepository> lostItemInRepositories);

    void addSourceFile(SourceFileInRepository sourceFileInRepository);

    void addLostPropertyOffice(LostPropertyOffice office);

    List<ItemInRepository> findByItemDescription(String itemDescription, String cityName);

    LostPropertyOffice findLostPropertyOffice(String cityName);

    List<ItemInRepository> returnAllItemsFromOffice(String office);

    void deleteLostPropertyOffice(String officeName);

    void deleteLostItems(List<ItemInRepository> items);

    //void deleteSourceFile(SourceFileInRepository sourceFileInRepository);

}

