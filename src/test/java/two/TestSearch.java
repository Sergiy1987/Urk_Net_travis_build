package two;

import core.TestBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static core.Application.atLoginPage;
import static core.Application.atProfilePage;
import static data.LoginTestData.*;

public class TestSearch extends TestBase {
    @BeforeClass
    public void setUpSearch() throws Exception {
        atLoginPage()
                .cleanUp()
                .signIn(USER_LOGIN, USER_PASSWORD);
        atProfilePage()
                .assertLoginSuccess(ACCOUNT_EMAIL);
    }

    @AfterClass
    public void signOut() {
        atProfilePage()
                .signOut();
    }

    @Test
    public void searchByEmail() {
        atProfilePage()
                .search("nedved198725@gmail.com")
                .assertEmailPresents("nedved198725@gmail.com");
    }

}
