package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class ContactAdressTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (! app.contact().isThereContact()) {
            app.contact().create(new ContactData().withFirstName("Olha").withLastName("Mezhova").withMiddleName("Igorevna")
                    .withFisrtMail("mail1").withHomePhone("homephone").withGroup("test1"));
        }
    }
@Test
public void testContactAddress() {
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));

}

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s","");
    }


}
