package org.b2m.lostandfound;

import java.util.Date;

public class Item {
    private String  name;
    private Date    findDate;
    private Date    receiveDate;
    private String  cityCode;
    private String  findPlace;
    private String  cityName;

    public Item() {
    }

    public Item(String name, Date findDate, Date receiveDate, String cityCode, String findPlace, String cityName) {
        this.name = name;
        this.findDate = findDate;
        this.receiveDate = receiveDate;
        this.cityCode = cityCode;
        this.findPlace = findPlace;
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public Date getFindDate() {
        return findDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getFindPlace() {
        return findPlace;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (findDate != null ? !findDate.equals(item.findDate) : item.findDate != null) return false;
        if (receiveDate != null ? !receiveDate.equals(item.receiveDate) : item.receiveDate != null) return false;
        if (cityCode != null ? !cityCode.equals(item.cityCode) : item.cityCode != null) return false;
        if (findPlace != null ? !findPlace.equals(item.findPlace) : item.findPlace != null) return false;
        return cityName != null ? cityName.equals(item.cityName) : item.cityName == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (findDate != null ? findDate.hashCode() : 0);
        result = 31 * result + (receiveDate != null ? receiveDate.hashCode() : 0);
        result = 31 * result + (cityCode != null ? cityCode.hashCode() : 0);
        result = 31 * result + (findPlace != null ? findPlace.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", findDate=" + findDate +
                ", receiveDate=" + receiveDate +
                ", cityCode='" + cityCode + '\'' +
                ", findPlace='" + findPlace + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
