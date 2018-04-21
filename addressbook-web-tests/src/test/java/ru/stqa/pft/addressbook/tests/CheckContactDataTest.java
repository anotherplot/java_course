package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CheckContactDataTest extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (! app.contact().isThereContact()) {
            app.contact().create(new ContactData().withFirstName("Olha").withLastName("Mezhova").withMiddleName("Igorevna")
                    .withHomePhone("+7(900)222333444").withMobilephone("33 33 22").withWorkPhone("908844474")
                    .withFisrtMail("mail1@tes.tt").withSecondMail("mail2@tes.tt").withThirdMail("mail3@tes.tt")
                    .withAddress("100220, Russia, Moscow, White street, b.3, app.1"));
        }
    }

    @Test
    public void testContactData() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));
        assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    }

    private String mergePhones(ContactData contact) {

        return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
                .stream().filter((s) -> ! s.equals(""))
                .map(CheckContactDataTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeMails(ContactData contact) {

        return Arrays.asList(contact.getFirstmail(), contact.getSecondmail(), contact.getThirdmail())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }


    public static String cleaned(String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }


}
