package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class AddContactToGroup extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {
       ContactData contact = app.db().contacts().iterator().next();
       Groups contactGroups = contact.getGroups();

     if(contactGroups.size() == app.db().groups().size() || app.db().groups().size() == 0){

            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        if (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/photo.png");
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Olha").withLastName("Mezhova").withMiddleName("Igorevna")
                    .withFisrtMail("mail1").withSecondMail("mail2").withThirdMail("mail3").withHomePhone("900").withMobilephone("888").withWorkPhone("9330009988").withPhoto(photo));

        }


    }

    @Test
    public void testAddContactToGroup() {

        ContactData choosenContact = app.db().contacts().iterator().next();
        Groups contactGroupsBefore = choosenContact.getGroups();

        Groups groups = app.db().groups();
        GroupData chosenGroup = groups.iterator().next();

        if (contactGroupsBefore.size() != 0 && contactGroupsBefore.iterator().next().getId() == chosenGroup.getId())
            for (GroupData existingGroup : groups) {
                    if (! contactGroupsBefore.contains(existingGroup)) {
                        chosenGroup = existingGroup;
                        System.out.println("found another free group"+existingGroup);
                        break;
                    }

                }

        System.out.println("chosen another free group"+chosenGroup);

        app.goTo().homePage();
        app.contact().selectContactById(choosenContact.getId());
        choosenContact.inGroup(chosenGroup);
        app.contact().addGroupTo(choosenContact, chosenGroup);
        Groups contactGroupsAfter = choosenContact.getGroups();
        assertThat(contactGroupsBefore.size()+1, equalTo(contactGroupsAfter.size()));
        assertTrue(contactGroupsAfter.contains(chosenGroup));

    }


}
