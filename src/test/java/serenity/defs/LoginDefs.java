package serenity.defs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import serenity.stepLibraries.LoginSteps;

public class LoginDefs {
    @Steps
    LoginSteps login;

    @Given("^user is on Home Page$")
    public void user_is_on_Home_Page() {
        login.open_home_page();
        login.verify_access_web();
    }

    @And("^user logs in to system with username is \"([^\"]*)\" and password is \"([^\"]*)\"$")
    public void user_logs_in_to_system_with_username_is_and_password_is(String username, String pass) {
        login.user_logs_in_with_username_and_password(username, pass);
    }

    @Then("^user logs in \"([^\"]*)\" and return message is \"([^\"]*)\"$")
    public void userLogsInAndReturnMessageIs(String result, String mess) {
        if (result.equals("passed")) {
            login.verify_login_successfully();
        } else if (result.equals("failed")) {
            login.verify_login_fail(mess);
        }
    }
}
