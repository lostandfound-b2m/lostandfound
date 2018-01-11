package org.b2m.lostandfound;

import java.util.Date;

public class ItemInRest {
    private String name;
    private Date dateFound;
    private Date dateReceived;
    private String cityCode;
    private String cityName;
    private String placeFound;
    private String officeName;
    private LostPropertyOffice lostPropertyOffice;

    public ItemInRest(ItemInRepository itemInRepository) {
        name = itemInRepository.getName();
        dateFound = itemInRepository.getFindDate();
        dateReceived = itemInRepository.getReceiveDate();
        cityCode = itemInRepository.getCityCode();
        cityName = itemInRepository.getCityName();
        placeFound = itemInRepository.getFindPlace();
        officeName = itemInRepository.getOfficeName();
        lostPropertyOffice = itemInRepository.getLostPropertyOffice();
    }

    public ItemInRest(String name, Date dateFound, Date dateReceived, String cityCode, String cityName,
                      String placeFound, String officeName, LostPropertyOffice lostPropertyOffice) {
        this.name = name;
        this.dateFound = dateFound;
        this.dateReceived = dateReceived;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.placeFound = placeFound;
        this.officeName = officeName;
        this.lostPropertyOffice = lostPropertyOffice;
    }

    @Override
    public String toString() {
        return "ItemInRest{" +
                "name='" + name + '\'' +
                ", dateFound=" + dateFound +
                ", dateReceived=" + dateReceived +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", placeFound='" + placeFound + '\'' +
                ", officeName='" + officeName + '\'' +
                ", lostPropertyOffice=" + lostPropertyOffice +
                '}';
    }

    public String getName() {
        return name;
    }

    public Date getDateFound() {
        return dateFound;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getPlaceFound() {
        return placeFound;
    }

    public String getOfficeName() {
        return officeName;
    }

    public LostPropertyOffice getLostPropertyOffice() {
        return lostPropertyOffice;
    }
}
