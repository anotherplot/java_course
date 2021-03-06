package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        attach(By.name("photo"), contactData.getPhoto());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("email"), contactData.getFirstmail());
        type(By.name("email2"), contactData.getSecondmail());
        type(By.name("email3"), contactData.getThirdmail());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }


    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    public void initContactModification(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();


        //click(By.xpath("//input[@value=" + id + "]/../following-sibling::*[7]/a/img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void initContactDeletion() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void submitContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCash = null;
        returnToHomePage();
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private Contacts contactCash = null;

    public Contacts all() {
        if (contactCash != null) {
            return new Contacts(contactCash);
        }
        contactCash = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        //List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String surname = cells.get(1).getText();
            String name = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String allMails = cells.get(4).getText();
            String address = cells.get(3).getText();
            ContactData c = new ContactData().withId(id).withFirstName(name).withLastName(surname).withAllphones(allPhones).withAllMails(allMails).withAddress(address);
            contactCash.add(c);


        }
        return contactCash;
    }

    public void gotoHomePage() {

        if (isElementPresent(By.id("maintable"))) {
            return;
        }

        click(By.linkText("home"));
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        initContactDeletion();
        submitContactDeletion();
        contactCash = null;
        gotoHomePage();

    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCash = null;
        gotoHomePage();

    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String fisrtname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
        String firstmail = wd.findElement(By.name("email")).getAttribute("value");
        String secondmail = wd.findElement(By.name("email2")).getAttribute("value");
        String thirdmail = wd.findElement(By.name("email3")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String workpphone = wd.findElement(By.name("work")).getAttribute("value");
        String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(fisrtname).withLastName(lastname).withMiddleName(middlename).withFisrtMail(firstmail).
                withHomePhone(homephone).withWorkPhone(workpphone).withMobilephone(mobilephone)
                .withFisrtMail(firstmail).withSecondMail(secondmail).withThirdMail(thirdmail)
                .withAddress(address);
    }

    public void addToGroup(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(group.getId()));
        click(By.name("add"));

    }


    public GroupData addGroupToContact(ContactData choosenContact, Groups contactGroupsBefore, Groups groups, GroupData chosenGroup) {

        if (contactGroupsBefore.size() != 0 && contactGroupsBefore.iterator().next().getId() == chosenGroup.getId())
            for (GroupData existingGroup : groups) {
                if (! contactGroupsBefore.contains(existingGroup)) {
                    chosenGroup = existingGroup;
                    System.out.println("selected " +chosenGroup);
                    break;
                }

            }
        selectContactById(choosenContact.getId());
        choosenContact.inGroup(chosenGroup);
        addToGroup(chosenGroup);
        click(By.partialLinkText("group page"));
        return chosenGroup;
    }

    public void deleteContactFromGroup(ContactData contact, GroupData group){

        removeFromGroup(contact, group);
        click(By.partialLinkText("group page"));
        contact.removedGromGroup(group);
        click(By.partialLinkText("group page"));

    }

    public void removeFromGroup(ContactData contact, GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByValue(Integer.toString(group.getId()));
        selectContactById(contact.getId());
        click(By.name("remove"));

    }


}
