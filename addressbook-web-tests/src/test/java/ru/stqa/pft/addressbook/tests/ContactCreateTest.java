package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreateTest extends TestBase{

    @Test
    public void contactCreationTest() {

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Olga", "Igorevna", "Mezhova", "8001102020", "8556006060", "mail1@test.ru", "mail2@test.ru", "mail3@test.ru", "test1"),true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }


}
