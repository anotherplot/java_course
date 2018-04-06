package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificateTest extends TestBase {


  @Test (enabled = false)
  public void testContactModification() {

    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Olga", "Igorevna", "Mezhova", "8001102020", "8556006060", "mail1@test.ru", "mail2@test.ru", "mail3@test.ru", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size()-1);
    ContactData contact = new ContactData("newname", "newmiddle", "newlast", "9991112233", "7998887766", "new1@mail.ru", "new2@mail.ru", "new3@mail.ru", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(contact);

    Comparator<? super ContactData> byName = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byName);
    after.sort(byName);
    Assert.assertEquals(before,after);




  }


}

