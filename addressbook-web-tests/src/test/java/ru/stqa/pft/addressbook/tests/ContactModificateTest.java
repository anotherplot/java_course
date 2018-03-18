package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificateTest extends TestBase {


  @Test
  public void testContactModification() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("newname", "newmiddle", "newlast", "9991112233", "7998887766", "new1@mail.ru", "new2@mail.ru", "new3@mail.ru"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();

  }


}

