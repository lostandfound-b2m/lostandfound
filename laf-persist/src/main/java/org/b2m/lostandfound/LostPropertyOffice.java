package org.b2m.lostandfound;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "LostPropertyOffice")
@Table(name = "lost_property_office")
public class LostPropertyOffice {

    @Id
    @Column(name = "office_name")
    private String officeName;

    @Column(name="city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email_address")
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

    @Override
    public String toString() {
        return "LostPropertyOffice{" +
                "officeName='" + officeName + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
