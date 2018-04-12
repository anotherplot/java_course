package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private String firstname;
  private String middlename;
  private String lastname;
  private String homephone;
  private String mobilephone;
  private String workphone;
  private String firstmail;
  private String secondmail;
  private String thirdmail;
  private String group;
  private int id = Integer.MAX_VALUE;


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
