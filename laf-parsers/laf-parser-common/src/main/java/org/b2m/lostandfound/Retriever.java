package org.b2m.lostandfound;

import java.io.IOException;
import java.util.List;

public interface Retriever {
    List<SourceFile> retrieveFiles(String url);
    List<Item> retrieveItemsFromFiles(List<SourceFile> files) throws IOException;

}
