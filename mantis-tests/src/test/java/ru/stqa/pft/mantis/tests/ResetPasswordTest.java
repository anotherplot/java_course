package ru.stqa.pft.mantis.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void resetPassword() throws IOException, MessagingException {
        String newPassword = "changed_password";
        Users userlist = app.db().users();
        UserData chosenUser = userlist.iterator().next();
        app.registration().logIn(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.registration().resetPassword(chosenUser);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, chosenUser.getEmail());
        app.registration().finish(confirmationLink,newPassword);
        AssertJUnit.assertTrue(app.newSession().login(chosenUser.getUsername(), newPassword));
    }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        System.out.println(mailMessage);
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
