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
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DeleteContactFromGroupTest extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {

        //cоздаем контакт, если в бд нет контактов
        if (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/photo.png");
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Olha").withLastName("Mezhova").withMiddleName("Igorevna")
                    .withFisrtMail("mail1").withSecondMail("mail2").withThirdMail("mail3").withHomePhone("900").withMobilephone("888").withWorkPhone("9330009988").withPhoto(photo));

        }

        ContactData contact = app.db().contacts().iterator().next();
        Groups contactGroups = contact.getGroups();
        //если у контакта нет группы проверяем есть ли группы в бд, если нет - создаем
        if(contactGroups.size() == 0){
            if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            }
            //если группы в бд есть - добавляем контакту первую из списка группу
                Groups groups = app.db().groups();
                GroupData chosenGroup = groups.iterator().next();
                app.goTo().homePage();
                app.contact().addGroupToContact(contact, contact.getGroups(), groups, chosenGroup);
            }

    }

    @Test
    public void testDeleteContactFromGroup() throws InterruptedException {

        ContactData choosenContact = app.db().contacts().iterator().next();
        Groups contactGroupsBefore = choosenContact.getGroups();
        GroupData deletedGroup = choosenContact.getGroups().iterator().next();
        System.out.println("choosen contact"+choosenContact);
        System.out.println("choosen group"+deletedGroup);

        app.goTo().homePage();
        app.contact().deleteContactFromGroup(choosenContact,deletedGroup);
        Groups contactGroupsAfter = choosenContact.getGroups();
        assertThat(contactGroupsBefore.size(), equalTo(contactGroupsAfter.size()+1));
        assertFalse(contactGroupsAfter.contains(deletedGroup));



    }


}
