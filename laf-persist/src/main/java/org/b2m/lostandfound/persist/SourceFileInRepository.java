package org.b2m.lostandfound.persist;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "source_file")
public class SourceFileInRepository {
    @Id
    private String fileName;

    //@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_name")
    private String lostPropertyOffice;

    @Column(name = "file_date")
    private LocalDate fileDate;

    @Column(name = "update_checker")
    private String updateChecker;

    @Column(name = "url_address")
    private String urlAddress;

    public SourceFileInRepository() {
    }

    ;

    public SourceFileInRepository(String fileName, String lostPropertyOffice, LocalDate fileDate, String updateChecker, String urlAddress) {
        this.fileName = fileName;
        this.lostPropertyOffice = lostPropertyOffice;
        this.fileDate = fileDate;
        this.updateChecker = updateChecker;
        this.urlAddress = urlAddress;
    }

}
