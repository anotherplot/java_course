package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreateTest extends TestBase {

  @Test
  public void contactCreationTest() {

    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
      File photo = new File("src/test/resources/photo.png");
    ContactData contact = new ContactData().withFirstName("test1").withMiddleName("test2").withLastName("test3")
            .withHomePhone("111").withMobilephone("222").withWorkPhone("333").withGroup("test2").withPhoto(photo);
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}


