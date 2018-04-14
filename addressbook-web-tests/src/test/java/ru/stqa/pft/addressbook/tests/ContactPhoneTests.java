package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (! app.contact().isThereContact()) {
            app.contact().create(new ContactData().withFirstName("Olha").withLastName("Mezhova").withMiddleName("Igorevna")
                    .withHomePhone("+7(900)222333444").withMobilephone("33 33 22").withWorkPhone("908844474").withGroup("test1"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {

        return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }


}
