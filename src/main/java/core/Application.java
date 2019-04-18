package core;

import pages.*;

import static com.codeborne.selenide.Selenide.page;

public class Application {

    public static HomePage atHomePage() {
        return page(HomePage.class);
    }

    public static LoginPage atLoginPage() {
        return page(LoginPage.class);
    }

    public static LogoutPage atLoogoutPage() {
        return page(LogoutPage.class);
    }

    public static ProfilePage atProfilePage() {
        return page(ProfilePage.class);
    }

    public static SearchResultsPage atSearchResultsPage() {
        return page(SearchResultsPage.class);
    }

}
