package org.b2m.lostandfound;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "source_file")
public class SourceFileInRepository {
    @Id
    @Column(name = "filename")
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_name")
    private LostPropertyOffice lostPropertyOffice;

    @Column(name = "update_checker")
    private String updateChecker;

    @Column(name = "url_address")
    private String urlAddress;

    public SourceFileInRepository() {
    }


    public SourceFileInRepository(String fileName, LostPropertyOffice lostPropertyOffice, String updateChecker, String urlAddress) {
        this.fileName = fileName;
        this.lostPropertyOffice = lostPropertyOffice;
        this.updateChecker = updateChecker;
        this.urlAddress = urlAddress;
    }

    public String getUrl() {return urlAddress;}
    public String getName() {return fileName;}
    public String getUpdateChecker() {return updateChecker;}
    public LostPropertyOffice getOffice() {return lostPropertyOffice;}
}
