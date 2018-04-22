package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

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
        Groups contactGroups = choosenContact.getGroups();

        Groups groups = app.db().groups();
        GroupData chosenGroup = groups.iterator().next();

        if (contactGroups.size() != 0 && contactGroups.iterator().next().getId() == chosenGroup.getId())
            for (GroupData existingGroup : groups) {
                    if (! contactGroups.contains(existingGroup)) {
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

    }


}
