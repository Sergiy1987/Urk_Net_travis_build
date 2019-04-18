package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class LoginPage {
    // Avoid too fast chromedriver issues
    public static void smartInput(SelenideElement element, String value) {
        while (!element.getValue().equals(value)) {
            element.setValue(value);
        }
    }

    public LoginPage signIn(String login, String password) {
        $("#id-l").val(login);
        $("#id-p").val(password);
        $("button[type='submit']").hover().click();
        return this;
    }

    public LoginPage cleanUp() {
        clearBrowserCache();
        return this;
    }

    public LoginPage clickOnButton(String nameButton) {
        ElementsCollection buttons = $$("span[class='header__lang-long-name']").filterBy(text(nameButton));
        //ElementsCollection buttons = $$("span[class='header__lang-long-name']");
        for (SelenideElement button : buttons) {
            button.shouldHave(text(nameButton)).hover().click();
            sleep(500);
        }
        /*for (int i = 0; i < buttons.size(); i++) {
            buttons.find(text(nameButton)).hover().click();
        }*/
        return this;
    }

    public void assertLoginFailed() {
        $(".form__error.form__error_fail").shouldHave(text("Неправильні дані"));
    }

    public void assertEmptyFieldsErrors() {
        $(".form__error.form__error_fail").shouldHave(text("Поле має бути заповнене"));
    }

    public void assertLongCredentialsFieldsErrors() {
        $(".form__error.form__error_fail").shouldHave(text("Неправильні дані"));
    }

    public void assertLanguageLogo(String logoCountry) {
        $(".header__logo.header__logo_lang-" + logoCountry).shouldBe(visible);
    }
}
