package tests;

import data.Language;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTests extends TestBase {

    @EnumSource(Language.class)
    @Tag("SMOKE")
    @ParameterizedTest(name = "Проверка заголовка на соответствующем языке {0}")
    void checkTitleOnOtherLanguagesTest(Language language) {
        open("/");
        $(".header__block_locale").click();
        $("div").$(byText(language.name())).click();
        $("div.question__list__title").shouldHave(text(language.description));
    }

    static Stream<Arguments> patternSiteShouldBeCorrectButtonsTest() {
        return Stream.of(
                Arguments.of(Language.RU,
                        List.of("О нас", "Проекты", "Услуги", "Карьера", "Контакты")
                ),
                Arguments.of(Language.EN,
                        List.of("About Us", "Projects", "Services", "Contacts")
                ),
                Arguments.of(Language.HY,
                        List.of("Մեր մասին", "Նախագծեր", "Ծառայություններ", "Հետադարձ կապ")
                )
        );
    }

    @MethodSource
    @Tag("WEB")
    @ParameterizedTest(name = "Проверка наличия кнопок {1} на соответствующем языке {0}")
    void patternSiteShouldBeCorrectButtonsTest(Language language, List<String> expectedButtons) {
        open("/");
        $(".header__block_locale").click();
        $("div").$(byText(language.name())).click();
        $$("div.header__links a").shouldHave(texts(expectedButtons));

    }

    @CsvFileSource(resources = "/test_data/checkTitleOnOtherLanguagesWithFile.csv")
    @Tag("SMOKE")
    @ParameterizedTest(name = "Проверка заголовка {1} на соответствующем языке {0}")
    void checkTitleOnOtherLanguagesWithCSVFileTest(String language, String title) {
        open("/");
        $(".header__block_locale").click();
        $("div").$(byText(language)).shouldBe(visible).click();
        $("div.question__list__title").shouldHave(text(title));
    }
}
