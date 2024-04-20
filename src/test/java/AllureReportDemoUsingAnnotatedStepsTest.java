import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllureReportDemoUsingAnnotatedStepsTest extends TestBase {

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
        AllureReportDemoUsingAnnotatedStepsWebSteps webSteps = new AllureReportDemoUsingAnnotatedStepsWebSteps();
        webSteps.openMainPage();
        webSteps.clickSearchBox();
        webSteps.enterSearchQueryAndPressEnter(searchQuery);
        webSteps.clickLink(searchRepo);
        webSteps.goToIssuesTab();
        webSteps.checkIssueExistence(searchIssue);
        webSteps.takeScreenshot();
        webSteps.savePageSource();
    }
}
