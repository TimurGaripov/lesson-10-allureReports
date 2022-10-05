package guru.qa.allure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class TestAllure {
    private static final String REPOSITORY = "apache/jmeter";
    private static final int ISSUE = 5709;

    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @DisplayName("Тест поиска Issue")
    @Test
    @Feature("Allure reports - Issue в репозитории")
    @Story("QA.GURU обучение")
    @Owner("garipovtr")
    @Severity(SeverityLevel.NORMAL)
    public void testIssueSearch() {
        open("https://github.com/");
        $("[placeholder='Search GitHub']").setValue("apache/jmeter").pressEnter();
        $(linkText("apache/jmeter")).click();
        $("#issues-tab").click();
        $(withText("#5709")).should(exist);

    }

    @DisplayName("Тест поиска Issue с использованием Lambda")
    @Test
    @Feature("Allure reports - Issue в репозитории")
    @Story("QA.GURU обучение")
    @Owner("garipovtr")
    @Severity(SeverityLevel.NORMAL)
    public void testIssueSearchWithLambda() {
        step("Открываем страницу GitHub", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий" + " " + REPOSITORY, () -> {
            $("[placeholder='Search GitHub']").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозитория" + " " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue c номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(exist);
        });
    }

    @DisplayName("Тест поиска Issue с аннотациями")
    @Test
    @Feature("Allure reports - Issue в репозитории")
    @Story("QA.GURU обучение")
    @Owner("garipovtr")
    @Severity(SeverityLevel.NORMAL)
    public void testAnnotatedStep() {
        TestWebSteps steps = new TestWebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }

}
