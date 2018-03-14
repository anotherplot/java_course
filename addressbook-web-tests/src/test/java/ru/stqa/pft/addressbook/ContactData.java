package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String homephone;
  private final String workphone;
  private final String firstmail;
  private final String secondmail;
  private final String thirdmail;

  public ContactData(String firstname, String middlename, String lastname, String homephone, String workphone, String firstmail, String secondmail, String thirdmail) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.homephone = homephone;
    this.workphone = workphone;
    this.firstmail = firstmail;
    this.secondmail = secondmail;
    this.thirdmail = thirdmail;
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
}
