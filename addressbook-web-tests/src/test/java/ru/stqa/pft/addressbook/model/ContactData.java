package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {
  @Id
  @Column(name="id")
  private int id;

  @Expose
  @Column(name="firstname")
  private String firstname;

  @Expose
  @Column(name="middlename")
  private String middlename;

  @Expose
  @Column(name="lastname")
  private String lastname;

  @Expose
  @Column(name="home")
  @Type(type = "text")
  private String homephone;

  @Expose
  @Column(name="mobile")
  @Type(type = "text")
  private String mobilephone;

  @Expose
  @Column(name="work")
  @Type(type = "text")
  private String workphone;

  @Expose
  @Column(name="email")
  @Type(type = "text")
  private String firstmail;

  @Expose
  @Column(name="email2")
  @Type(type = "text")
  private String secondmail;

  @Expose
  @Column(name="email3")
  @Type(type = "text")
  private String thirdmail;

  @Expose
  @Transient
  private String group;

  @Expose
  @Column(name="photo")
  @Type(type = "text")
  private String photo;

  @Transient
  private String allphones;
  @Transient
  private String allMails;

  @Column(name="address")
  @Type(type = "text")
  private String address;

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }



    public String getAddress() {
        return address;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }



  public String getAllphones() {
    return allphones;
  }

  public ContactData withAllphones(String allphones) {
    this.allphones = allphones;
    return this;

  }

    public String getAllMails() {
        return allMails;
    }

    public ContactData withAllMails(String allMails) {
        this.allMails = allMails;
        return this;

    }


  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getFirstmail() {
    return firstmail;
  }

  public String getSecondmail() {
    return secondmail;
  }

  public String getThirdmail() {
    return thirdmail;
  }

  public String getGroup() {
    return group;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {

    return Objects.hash(firstname, lastname, id);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            "firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", homephone='" + homephone + '\'' +
            ", workphone='" + workphone + '\'' +
            ", firstmail='" + firstmail + '\'' +
            ", secondmail='" + secondmail + '\'' +
            ", thirdmail='" + thirdmail + '\'' +
            ", group='" + group + '\'' +
            '}';
  }


  public ContactData withFirstName(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddleName(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastName(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withHomePhone(String homephone) {
    this.homephone = homephone;
    return this;
  }


  public ContactData withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public ContactData withWorkPhone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withFisrtMail(String firstmail) {
    this.firstmail = firstmail;
    return this;
  }

  public ContactData withSecondMail(String secondmail) {
    this.secondmail = secondmail;
    return this;
  }

  public ContactData withThirdMail(String thirdmail) {
    this.thirdmail = thirdmail;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public int getId() {
    return id;
  }


}
