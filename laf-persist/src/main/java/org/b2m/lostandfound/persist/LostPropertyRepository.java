package org.b2m.lostandfound.persist;
import java.io.Serializable;
import java.util.List;

public interface LostPropertyRepository<T> {

    public void persist(T entity);
    public void update(T entity);
    public void delete(T entity);
    //public List<T> findByItemDescription(String description);




}

