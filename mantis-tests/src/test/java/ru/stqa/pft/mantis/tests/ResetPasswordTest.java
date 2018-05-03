package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {


    @Test
    public void resetPassword() {
        Users userlist = app.db().users();
        UserData chosenUser = userlist.iterator().next();
        app.registration().logIn(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.registration().manageUser(chosenUser);



    }
}
