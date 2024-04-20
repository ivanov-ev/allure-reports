import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.attachment;
import static org.openqa.selenium.By.linkText;

public class AllureReportDemoUsingAnnotatedStepsWebSteps extends TestBase {

    @Step("Open the main page")
    public void openMainPage() {
        open(baseUrl);
    }

    @Step("Click the search box")
    public void clickSearchBox() {
        $(".search-input-container").click();
    }

    @Step("Enter the search query '{searchQuery}' into the the search box, and press <Enter>")
    public void enterSearchQueryAndPressEnter(String searchQuery) {
        $("#query-builder-test").sendKeys(searchQuery);
        $("#query-builder-test").pressEnter();
    }

    @Step("Click the link '{searchRepo}'")
    public void clickLink(String searchRepo) {
        $(linkText(searchRepo)).click();
    }

    @Step("Go to the 'Issues' tab")
    public void goToIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Check whether the issue '{searchIssue}' exists")
    public void checkIssueExistence(String searchIssue) {
        $(withText(searchIssue)).should(exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot)getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void savePageSource() {
        attachment("Page source", webdriver().driver().source());
    }
}
