import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import data.Language;

public class ParametrizedTests extends TestBase {

    @ParameterizedTest(name = "Успешный поиск на Яндекс.Маркете: {0}")
    @ValueSource(strings = {
            "зубная щетка", "ноутбук", "Play Station"
    })
    @DisplayName("Успешный поиск товаров на Яндекс.Маркете")
    void searchForProductsShouldHaveResults(String search) {
        open("https://market.yandex.ru/");
        $("#header-search").setValue(search).pressEnter();
        //$$(".n-snippet-list__item").shouldBe(sizeGreaterThan(0));
        $("[data-auto='title']").shouldHave(text(search));
    }
    @ParameterizedTest(name = "Язык {0} ")
    @EnumSource(Language.class)
    @DisplayName("Проверка локализации сайта через EnumSource")
    void novatekSiteShouldDisplayCorrectText(Language language) {
        open("https://www.novatek.ru/ru/");
        $("#lang").$(byText(language.name())).click();
        $(".flexrow").shouldHave(text(language.description));
    }
    @CsvSource(value = {
            "T123,test123",
            "TestUser99,invalidpassword"
    })
    @ParameterizedTest(name = "Ошибка при авторизации пользователя {0} с паролем {1} на сайте")
    void userAuthorizationOnSiteShouldBeFailed(String login, String password) {
        open("https://demoqa.com/login");
        $("#userName").setValue(login);
        $("#password").setValue(password);
        $("#login").click();
        $("#name").shouldHave(text("Invalid username or password!"));
    }

    @CsvFileSource(resources = "/cred.csv")
    @ParameterizedTest(name = "Ошибка при авторизации пользователя {0} с паролем {1} на сайте из файла")
    void userAuthorizationOnSiteShouldBeFailedWithCSVFile(String login, String password) {
        open("https://demoqa.com/login");
        $("#userName").setValue(login);
        $("#password").setValue(password);
        $("#login").click();
        $("#name").shouldHave(text("Invalid username or password!"));
    }
}
