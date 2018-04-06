package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String homephone;
  private final String workphone;
  private final String firstmail;
  private final String secondmail;
  private final String thirdmail;
  private String group;
  private int id;

  public ContactData(int id, String firstname, String middlename, String lastname, String homephone, String workphone, String firstmail, String secondmail, String thirdmail, String group) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.homephone = homephone;
    this.workphone = workphone;
    this.firstmail = firstmail;
    this.secondmail = secondmail;
    this.thirdmail = thirdmail;
    this.group = group;
  }


  public ContactData(String firstname, String middlename, String lastname, String homephone, String workphone, String firstmail, String secondmail, String thirdmail, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.homephone = homephone;
    this.workphone = workphone;
    this.firstmail = firstmail;
    this.secondmail = secondmail;
    this.thirdmail = thirdmail;
    this.group = group;
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


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }


  @Override
  public int hashCode() {

    return Objects.hash(firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
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
}
