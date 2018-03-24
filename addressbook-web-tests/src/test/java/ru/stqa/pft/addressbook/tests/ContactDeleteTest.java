package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeleteTest extends TestBase {

  @Test
  public void testContactDeletion(){

    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Olga", "Igorevna", "Mezhova", "8001102020", "8556006060", "mail1@test.ru", "mail2@test.ru", "mail3@test.ru", "test1"));
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().submitContactDeletion();



  }



}
