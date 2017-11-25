package org.b2m.lostandfound.persist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "LostPropertyOffice")
@Table(name = "lostpropertyoffice")
public class LostPropertyOffice {

    @Id
    @Column(name="officename")
    private String officeName;

    @OneToMany(
            mappedBy = "lostPropertyOffice",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<LostItem> lostItems = new ArrayList<>();

    @Column(name="city")
    private String city;

    @Column(name="postalcode")
    private String postalCode;

    @Column(name="contactnumber")
    private String contactNumber;

    @Column(name="emailaddress")
    private String email;

    public LostPropertyOffice() {
    }

    public LostPropertyOffice(String officeName, String city, String postalCode, String contactNumber, String email) {
        this.officeName = officeName;
        this.city = city;
        this.postalCode = postalCode;
        this.contactNumber = contactNumber;
        this.email = email;
    }
    public void addLostItem(LostItem newLostItem) {
        lostItems.add(newLostItem);

    }

    public void removeLostItem(LostItem oldLostItem) {
        lostItems.remove(oldLostItem);

    }

    public List<LostItem> getLostItems(){
        return lostItems;
    }

    public String getOfficeName() {
        return officeName;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }
}
