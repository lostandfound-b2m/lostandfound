package org.b2m.lostandfound;

import java.util.Date;

/**
 * Class for keeping URLs to PDF files found on Krakow L&F Office (PdfFilesRetriever Class),
 * along with file's name and last update date. Those attributes can be used later
 * to check if file was updated since last time we parsed data from it.
 */
public class PdfFile {
    private String url;
    private String name;
    private Date lastUpdate;

    public PdfFile() {
    }

    public PdfFile(String url, String name, Date lastUpdate) {
        this.url=url;
        this.name=name;
        this.lastUpdate=lastUpdate;
    }

    public String getUrl() {return url;}
    public void setUrl(String url) {
        this.url=url;
    }

    public String getName() {return name;}
    public void setName(String name) {
        this.name=name;
    }

    public Date getLastUpdate() {return lastUpdate;}
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate=lastUpdate;
    }

}
