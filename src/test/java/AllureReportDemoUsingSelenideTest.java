import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;

public class AllureReportDemoUsingSelenideTest extends TestBase {

    private final String searchQuery = "Selenide",
        searchRepo = "selenide/selenide",
        searchIssue = "User can add a global proxy filter";

    @Test
    @Feature("Github")
    @Story("Github Issue")
    @Owner("ivanov-ev")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "github", url = "https://github.com")
    @DisplayName("Search for a specific issue in a Github repository")
    public void findIssueInGithubTest () {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(baseUrl);
        $(".search-input-container").click();
        $("#query-builder-test").sendKeys(searchQuery);
        $("#query-builder-test").pressEnter();
        $(linkText(searchRepo)).click();
        $("#issues-tab").click();
        $(withText(searchIssue)).should(exist);
    }
}
