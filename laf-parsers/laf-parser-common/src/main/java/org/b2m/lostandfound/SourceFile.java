package org.b2m.lostandfound;

import java.time.LocalDate;

/**
 * Klasa przechowująca informacje o pliku znalezionym na stronie danego biura:
 * linku do pliku, nazwy pliku, checksumy umożliwiającej później zweryfikowanie, czy
 * plik został zaktualizowany oraz nazwy biura, które udostępnia ten plik.
 */
public class SourceFile {
    private String url;
    private String name;
    private String updateChecker;
    private String officeName;

    SourceFile() {};
    SourceFile(String url, String name, String updateChecker, String officeName) {
        this.url = url;
        this.name = name;
        this.updateChecker = updateChecker;
        this.officeName = officeName;
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

    public String getOfficeName() {return officeName;}
    public void setOfficeName(String officeName) {
        this.officeName=officeName;
    }
}
