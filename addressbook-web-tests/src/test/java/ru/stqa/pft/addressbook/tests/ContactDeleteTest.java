package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeleteTest extends TestBase {

  @Test
  public void testContactDeletion(){

    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereContact()) {
      app.getContactHelper().createContact(new ContactData("Olga", "Igorevna", "Mezhova", "8001102020", "8556006060", "mail1@test.ru", "mail2@test.ru", "mail3@test.ru", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().submitContactDeletion();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);




  }



}
