package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email){
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"),username);
        type(By.name("email"),email);
        click(By.cssSelector("input[value='Signup']"));

    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));

    }

    public void logIn(String username, String password) {
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"),username);
        click(By.cssSelector("input[value = 'Login']"));
        type(By.name("password"),password);
        click(By.cssSelector("input[value = 'Login']"));

    }

    public void manageUser(UserData user) {
        click(By.xpath("//a[contains(@href,'manage_overview')]"));
        click(By.xpath("//a[contains(@href,'manage_user_page')]"));
        click(By.xpath("//a[contains(@href,'user_id="+user.getId()+"')]"));



    }
}
