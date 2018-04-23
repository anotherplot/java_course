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

public class DeleteContactFromGroup extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {

        //проверять есть ли в группе контакты если нет то добавить в нее

        if (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/photo.png");
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Olha").withLastName("Mezhova").withMiddleName("Igorevna")
                    .withFisrtMail("mail1").withSecondMail("mail2").withThirdMail("mail3").withHomePhone("900").withMobilephone("888").withWorkPhone("9330009988").withPhoto(photo));

        }

        ContactData contact = app.db().contacts().iterator().next();
        Groups contactGroups = contact.getGroups();
        if(contactGroups.size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            //add group
        }

    }

    @Test
    public void testDeleteContactFromGroup() {

        // нужно выбрать контакт у которого есть группыб выбрать группу для удаления. перейти в просмотр контакта, нажать на эту группу и потом выбрать этот контакт и нажать удалить из группы
        //потом проверить что в списке групп этого контакта нет удаленной группы

        ContactData choosenContact = app.db().contacts().iterator().next();

        GroupData deletedGroup = choosenContact.getGroups().iterator().next();
        deletedGroup.getContacts().size();

        app.goTo().homePage();

    }


}
