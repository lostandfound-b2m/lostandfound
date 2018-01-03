package org.b2m.lostandfound.persist;

import java.util.List;

public interface LostPropertyRepository {

    public void persist(Object entity);

    public void delete(Object entity);

    public List<ItemDao> findByItemDescription(String itemDescription, String officeName);

    public List<ItemDao> returnAllItems(String objectName);

}

