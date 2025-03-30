package Day8;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.Page.GetByRoleOptions;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LearnBrowserContext {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch( new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        page.getByLabel("E-Mail Address").type("akshayghadi@flash.co");
        page.getByLabel("Password").type("T@hDud6vjQ3cEX9");
        page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Login")).click();
        Locator myAccount = page.getByText("Edit your account information");
        assertThat(myAccount).isVisible();

        //Open the page in the new tab and check if we are still logged in - open two tabs in same window
        Page newPage = browserContext.newPage();
        newPage.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        assertThat(myAccount).isVisible();
        //playwright.close();

        //Opening entire new browser context - Opens in new browser window
        BrowserContext browserContext2 = browser.newContext();
        Page newPage2 = browserContext2.newPage();
        newPage2.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");

        //Open firefox browser
        Browser browser1 = Playwright.create().firefox().launch( new  BrowserType.LaunchOptions().setHeadless(false));
        Page page1 = browser1.newPage();
        page1.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account")
;    }
}
