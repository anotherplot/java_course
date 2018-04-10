package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;

public class ContactModificateTest extends TestBase {


  @Test (enabled = false)
  public void testContactModification() {

    app.goTo().gotoHomePage();
    if (!app.contact().isThereContact()) {
     // app.contact().createContact(new ContactData("Olga", "Igorevna", "Mezhova", "8001102020", "8556006060", "mail1@test.ru", "mail2@test.ru", "mail3@test.ru", "test1"));
    }
    Contacts before = app.contact().all();
    app.contact().initContactModification(before.size()-1);
    ContactData contact = new ContactData().withFirstName("new1").withMiddleName("new2").withLastName("new3");
    app.contact().submitContactModification();
    app.goTo().gotoHomePage();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(contact);

    Comparator<? super ContactData> byName = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());

    Assert.assertEquals(before,after);




  }


}

