package org.b2m.lostandfound;


import java.util.Date;

public class Item {
    private String  name;
    private Date foundDate;
    private Date receiveDate;
    private String  cityCode;
    private String  foundPlace;
    private String  cityName;
    private String officeName;

    public Item() {
    }
    public Item(String cityName) {
        this.cityName = cityName;
        this.officeName = officeName;
    }

    public Item(String name, Date foundDate, Date receiveDate, String cityCode, String foundPlace, String cityName) {
        this.name = name;
        this.foundDate = foundDate;
        this.receiveDate = receiveDate;
        this.cityCode = cityCode;
        this.foundPlace = foundPlace;
        this.cityName = cityName;
        this.officeName = cityName;
    }

    public Item(String name, Date foundDate, String cityCode, String cityName) {
        this.name = name;
        this.foundDate = foundDate;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.officeName = cityName;
    }

    public String getName() {
        return name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getFoundPlace() {
        return foundPlace;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", foundDate=" + foundDate +
                ", receiveDate=" + receiveDate +
                ", cityCode='" + cityCode + '\'' +
                ", foundPlace='" + foundPlace + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (foundDate != null ? !foundDate.equals(item.foundDate) : item.foundDate != null) return false;
        if (receiveDate != null ? !receiveDate.equals(item.receiveDate) : item.receiveDate != null) return false;
        if (cityCode != null ? !cityCode.equals(item.cityCode) : item.cityCode != null) return false;
        if (foundPlace != null ? !foundPlace.equals(item.foundPlace) : item.foundPlace != null) return false;
        return cityName != null ? cityName.equals(item.cityName) : item.cityName == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (foundDate != null ? foundDate.hashCode() : 0);
        result = 31 * result + (receiveDate != null ? receiveDate.hashCode() : 0);
        result = 31 * result + (cityCode != null ? cityCode.hashCode() : 0);
        result = 31 * result + (foundPlace != null ? foundPlace.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        return result;
    }
}
