package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {


    @Test
    public void resetPassword() {
    app.registration().logIn(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.registration().manageUser();

    }
}
