import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureReportDemoUsingLambdaStepsTest extends TestBase {

    private final String searchQuery = "Selenide",
            searchRepo = "selenide/selenide",
            searchIssue = "User can add a global proxy filter";

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot)getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    @Feature("Github")
    @Story("Github Issue")
    @Owner("ivanov-ev")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "github", url = "https://github.com")
    @DisplayName("Search for a specific issue in a Github repository")
    public void findIssueInGithubTest () {
        step("Open the main page:" + baseUrl, () -> open(baseUrl));
        step("Click the search box", () -> $(".search-input-container").click());
        step("Enter the search string '" + searchQuery + "' into the the search box, and press <Enter>", () -> {
            $("#query-builder-test").sendKeys(searchQuery);
            $("#query-builder-test").pressEnter();
        });
        step("Click the link '" + searchRepo + "'", () -> $(linkText(searchRepo)).click());
        step("Go to the Issues tab", () -> $("#issues-tab").click());
        step("Check whether the issue with the name '" + searchIssue + "' exists", () ->
                $(withText(searchIssue)).should(exist));
        step("Screenshot", () -> takeScreenshot());
        step("Page source", () -> attachment("Page source", webdriver().driver().source()));

    }
}
