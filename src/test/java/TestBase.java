import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
    @BeforeAll
    static void setupEnvironment() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-infobars");
        Configuration.browserCapabilities = chromeOptions;
    }
}
