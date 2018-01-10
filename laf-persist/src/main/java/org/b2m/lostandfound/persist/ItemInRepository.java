package org.b2m.lostandfound.persist;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lostitem")
public class ItemInRepository {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office")
    private LostPropertyOffice lostPropertyOffice;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "file_name")
    //private SourceFileInRepository sourceFileDao;

    @Column(name = "name")
    private String name;

    @Column(name = "date_found")
    private Date dateFound;

    @Column(name = "date_received")
    private Date dateReceived;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "place_found")
    private String placeFound;

    @Column(name = "office_name")
    private String officeName;

    public ItemInRepository() {
    }

    public ItemInRepository(String name, Date dateFound, Date dateReceived, String cityCode, String placeFound, String cityName, LostPropertyOffice lostPropertyOffice) {

        this.name = name;
        this.dateFound = dateFound;
        this.dateReceived = dateReceived;
        this.cityCode = cityCode;
        this.placeFound = placeFound;
        this.cityName = cityName;
        this.officeName = cityName;
        this.lostPropertyOffice = lostPropertyOffice;
        //this.sourceFileDao = sourceFileDao;
    }

    public ItemInRepository(String name, Date dateFound, String cityCode, String cityName, LostPropertyOffice lostPropertyOffice) {
        this.name = name;
        this.dateFound = dateFound;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.lostPropertyOffice = lostPropertyOffice;
    }

    public String getName() {
        return name;
    }

    public Date getFindDate() {
        return dateFound;
    }

    public Date getReceiveDate() {
        return dateReceived;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getFindPlace() {
        return placeFound;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemInRepository item = (ItemInRepository) o;

        if (name != null ? !name.equals(item.name) : item.name != null)
            return false;
        if (dateFound != null ? !dateFound.equals(item.dateFound) : item.dateFound != null) return false;
        if (dateReceived != null ? !dateReceived.equals(item.dateReceived) : item.dateReceived != null) return false;
        if (cityCode != null ? !cityCode.equals(item.cityCode) : item.cityCode != null) return false;
        if (placeFound != null ? !placeFound.equals(item.placeFound) : item.placeFound != null) return false;
        return cityName != null ? cityName.equals(item.cityName) : item.cityName == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dateFound != null ? dateFound.hashCode() : 0);
        result = 31 * result + (dateReceived != null ? dateReceived.hashCode() : 0);
        result = 31 * result + (cityCode != null ? cityCode.hashCode() : 0);
        result = 31 * result + (placeFound != null ? placeFound.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LostItem{" +
                "name='" + name + '\'' +
                ", dateFound=" + dateFound +
                ", dateReceived=" + dateReceived +
                ", cityCode='" + cityCode + '\'' +
                ", placeFound='" + placeFound + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }


}
