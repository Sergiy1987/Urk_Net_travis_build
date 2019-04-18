package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static data.LoginTestData.LIVE_LOGIN_FORM_URL;
import static data.LoginTestData.LIVE_START_PAGE_URL;

public class TestBase {

    /*
    Cross browser testing:
    To setup needed browser pass the folowing option in commandline: -Dselenide.browser=""
    Run tests on Chrome "mvn clean test -Dselenide.browser=chrome"
    Run tests on Firefox: "mvn clean test -Dselenide.browser=firefox"
    Run tests on EDGE: "mvn clean test -Dselenide.browser=edge"
    For these tests Chrome is browser by default, if any browser starts, Firefox will run as selenide default browser
    Please note: test TestOperationsWithStartPage overrides setUp() method and will always be running in chrome headless
    browser.
    */
    private static WebDriver webDriver;
    private String browser = System.getProperty("selenide.browser", System.getProperty("browser", WebDriverRunner.CHROME));

    public static void takeScreenShoot() throws AWTException, IOException {
        Robot robot = new Robot();
        robot.mouseMove(0, 0);

        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(webDriver);
        File actualFile = new File("" + ".png");
        ImageIO.write(screenshot.getImage(), "png", actualFile);

    }

    /*
    In Selenide 4.7 and later you can comit a setup a driver via xxxDriverManager.getInstance().setup();
    Selenide do it under hood
     */
    @BeforeClass
    public void setUp() {
        switch (browser) {
            case "firefox":
                Configuration.browser = "firefox";
                Configuration.baseUrl = LIVE_START_PAGE_URL;
                Configuration.timeout = 10000;
                open(LIVE_LOGIN_FORM_URL);
                break;

            case "edge":
                Configuration.browser = "edge";
                Configuration.baseUrl = LIVE_START_PAGE_URL;
                Configuration.timeout = 10000;
                open(LIVE_LOGIN_FORM_URL);
                break;

            default:
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                Configuration.browser = "chrome";
                Configuration.baseUrl = LIVE_START_PAGE_URL;
                Configuration.timeout = 10000;
                DesiredCapabilities dc = new DesiredCapabilities();
                dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                Configuration.browserCapabilities = dc;
                open(LIVE_LOGIN_FORM_URL);

        }
    }

    @AfterClass
    public void tearDown() {
        close();
    }
}

