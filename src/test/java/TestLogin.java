import core.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static core.Application.atLoginPage;
import static core.Application.atProfilePage;
import static data.LoginTestData.*;

public class TestLogin extends TestBase {


    @Test
    public void liveLoginTest() {
        atLoginPage()
                .cleanUp()
                .signIn(USER_LOGIN, USER_PASSWORD);
        atProfilePage()
                .assertLoginSuccess(ACCOUNT_EMAIL)
                .signOut();
    }

    @Test(dataProvider = "Authentication_array")
    public void liveLoginArrayTest(String userLogin, String userPassword) {
        atLoginPage()
                .cleanUp()
                .signIn(userLogin, userPassword);
        atProfilePage()
                .assertLoginSuccess(ACCOUNT_EMAIL)
                .signOut();
    }

    @Test
    public void invalidEmailTest() {
        atLoginPage()
                .cleanUp()
                .signIn(INVALID_USER_LOGIN, USER_PASSWORD);
        atLoginPage()
                .assertLoginFailed();
    }

    @Test
    public void invalidPasswordTest() {
        atLoginPage()
                .cleanUp()
                .signIn(USER_LOGIN, INVALID_USER_PASSWORD);
        atLoginPage()
                .assertLoginFailed();
    }

    @Test
    public void emptyFieldsTest() {
        atLoginPage()
                .cleanUp()
                .signIn(EMPTY_ACCOUNT_LOGIN, EMPTY_ACCOUNT_PASSWORD);
        atLoginPage()
                .assertEmptyFieldsErrors();
    }

    @Test
    public void enterLongEmailTest() {
        atLoginPage()
                .cleanUp()
                .signIn(util.RandomData.getRandomString(130), USER_PASSWORD);
        atLoginPage()
                .assertLongCredentialsFieldsErrors();
    }

    @Test
    public void enterLongPassTest() {
        atLoginPage()
                .cleanUp()
                .signIn(USER_LOGIN, util.RandomData.getRandomString(130));
        atLoginPage()
                .assertLongCredentialsFieldsErrors();
    }

    @Test
    public void checkUaLanguageTest() {
        atLoginPage()
                .cleanUp()
                .clickOnButton(BUTTON_UA)
                .assertLanguageLogo(LOGO_UA);
    }

    @Test
    public void checkRuLanguageTest() {
        atLoginPage()
                .cleanUp()
                .clickOnButton(BUTTON_RU)
                .assertLanguageLogo(LOGO_RU);
    }

    @Test
    public void checkEngLanguageTest() {
        atLoginPage()
                .cleanUp()
                .clickOnButton(BUTTON_ENG)
                .assertLanguageLogo(LOGO_ENG);
    }

    @Test
    public void search() {
        atLoginPage()
                .cleanUp()
                .signIn(USER_LOGIN, USER_PASSWORD);
        sleep(3000);

        atProfilePage()
                .assertLoginSuccess(ACCOUNT_EMAIL)
                .signOut();
    }

    @DataProvider(name = "Authentication_array")
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{"nedved198725", "nedved1987"},
                new Object[]{"nedved198725", "nedved1987"},
                new Object[]{"nedved198725", "nedved1987"},
                new Object[]{"nedved198725", "nedved1987"}
        };
    }
}
