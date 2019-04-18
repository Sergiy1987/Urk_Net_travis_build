package pages;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    public ProfilePage assertLoginSuccess(String accountEmail) {
        $(".login-button__user").shouldHave(exactText(accountEmail));
        return this;
    }

    public void signOut() {
        //open(LIVE_LOGOUT_URL);
        $("a[class='login-button']").hover().click();
        sleep(2000);
        $(".login-popup__themes").should(appear);
        $("#login__logout").hover().click();
    }

    public ProfilePage search(String keyword) {
        $("input[type='text'][placeholder='Пошук']").val(keyword).pressEnter();
        return this;
    }

    public void assertEmailPresents(String keyword) {
        $$(byXpath("//*[starts-with(@id,'msg15')]")).filterBy(text(keyword)).shouldHave(sizeGreaterThan(0));

    }
}
