package org.b2m.lostandfound;

import java.util.Date;

/**
 * Class for keeping URLs to PDF files found on Krakow L&F Office (PdfFilesRetriever Class),
 * along with file name and last update date. Those attributes can be used later
 * to check if file was updated since last time we parsed data from it.
 */
public class SourceFile {
    private String url;
    private String name;
    private String updateChecker;

    public SourceFile() {
    }

    public SourceFile(String url, String name, String updateChecker) {
        this.url=url;
        this.name=name;
        this.updateChecker=updateChecker;
    }

    public String getUrl() {return url;}
    public void setUrl(String url) {
        this.url=url;
    }

    public String getName() {return name;}
    public void setName(String name) {
        this.name=name;
    }

    public String getUpdateChecker() {return updateChecker;}
    public void setUpdateChecker(String updateChecker) {
        this.updateChecker=updateChecker;
    }

}