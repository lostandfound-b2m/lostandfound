package org.b2m.lostandfound;

import java.util.List;

public interface FileRetriever {
    List<SourceFile> retrieve(String url);

}
