package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (! app.contact().isThereContact()) {
            app.contact().create(new ContactData().withFirstName("Olha").withLastName("Mezhova").withMiddleName("Igorevna").withFisrtMail("mail1").withHomePhone("homephone").withGroup("test1"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }


}
