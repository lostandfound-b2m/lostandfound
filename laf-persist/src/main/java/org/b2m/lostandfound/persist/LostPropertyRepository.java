package org.b2m.lostandfound.persist;

import java.util.List;

public interface LostPropertyRepository {

    void addLostItems(List<ItemDao> lostItemDaos);

    void addLostPropertyOffice(LostPropertyOffice office);

    List<ItemDao> findByItemDescription(String itemDescription, String cityName);

    List<ItemDao> returnAllItemsFromOffice(String office);

    void deleteLostPropertyOffice(String officeName);

    void deleteLostItems(List<ItemDao> items);

}

