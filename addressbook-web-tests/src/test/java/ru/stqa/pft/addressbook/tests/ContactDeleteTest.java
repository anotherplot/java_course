package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactDeleteTest extends TestBase {

  @Test (enabled = false)
  public void testContactDeletion(){

    app.goTo().gotoHomePage();
    if (! app.contact().isThereContact()) {
      //app.contact().create(new ContactData("Olga", "Igorevna", "Mezhova", "8001102020", "8556006060", "mail1@test.ru", "mail2@test.ru", "mail3@test.ru", "test1"));
    }
    Contacts before = app.contact().all();
    app.contact().selectContact(before.size() - 1);
    app.contact().initContactDeletion();
    app.contact().submitContactDeletion();
    app.goTo().gotoHomePage();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(before.size() - 1);

    Assert.assertEquals(before, after);



  }



}
