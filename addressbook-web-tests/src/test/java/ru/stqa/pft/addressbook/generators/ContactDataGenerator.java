package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex)  {
            jCommander.usage();
            return;
        }

        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
         saveAsJson(contacts, new File(file));
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }


    private List<ContactData> generateContacts(int count) throws IOException {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("first name %s",i))
                    .withFirstName(String.format("first name %s",i)).withMiddleName(String.format("middle name %s",i)).withLastName(String.format("last name %s",i))
                    .withFisrtMail(String.format("mail %s",i)).withSecondMail(String.format("mail2 %s",i)).withThirdMail(String.format("mail3 %s",i))
                    .withHomePhone(String.format("900555555%s",i)).withMobilephone(String.format("888000000 %s",i)).withWorkPhone(String.format("9330009988%s",i)));

        }
        return contacts;
    }
}
