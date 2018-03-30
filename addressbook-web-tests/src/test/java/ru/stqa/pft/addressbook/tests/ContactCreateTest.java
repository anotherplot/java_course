package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreateTest extends TestBase {

  @Test
  public void contactCreationTest() {

    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Olga", "Igorevna", "Mezhova", "8001102020", "8556006060", "mail1@test.ru", "mail2@test.ru", "mail3@test.ru", "test1");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    after.add(contact);
  }


}


