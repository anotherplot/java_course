package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreateTest extends TestBase{

    @Test
    public void contactCreationTest() {

        app.initContactCreation();
        app.fillContactForm(new ContactData("Olga", "Igorevna", "Mezhova", "8001102020", "8556006060", "mail1@test.ru", "mail2@test.ru", "mail3@test.ru"));
        app.submitContactCreation();
        app.returnToHomePage();
    }


}
