package serenity.stepLibraries;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import serenity.pages.HomePage;

import static serenity.commons.Constants.*;

public class LoginSteps {
    HomePage homePage;

    @Step
    public void open_home_page() {
        homePage.accessWebsite();
    }

    @Step
    public void verify_access_web() {
        String actualTitle = homePage.getTitleOfWebsite();
        Assert.assertEquals(EXPECTED_TITLE_OF_WEBSITE, actualTitle);
    }

    @Step
    public void user_logs_in_with_username_and_password(String username, String password) {
        homePage.enterLogin(username, password);
        homePage.clickLogin();
    }

    @Step
    public void verify_login_successfully() {
        Assert.assertEquals("Đăng nhập không thành công!", TEXT_IN_BUTTON_ACCOUNT, homePage.getTextOfBtnBesideCart());
    }

    @Step
    public void verify_login_fail(String mess) {
        Assert.assertEquals(mess, homePage.getMsgError());
    }

}
