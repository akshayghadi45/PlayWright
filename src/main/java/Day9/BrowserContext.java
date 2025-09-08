package Day9;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BrowserContext {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch( new BrowserType.LaunchOptions().setHeadless(false));
        com.microsoft.playwright.BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        page.getByPlaceholder("E-Mail Address").fill("akshayghadi@flash.co");
        page.getByPlaceholder("Password").fill("T@hDud6vjQ3cEX9");
        page.locator("#content > div > div:nth-child(2) > div > div > form > input").click();
        Locator locator = page.getByText("Edit your account information");
        assertThat(locator).isVisible();
        Page page1 = page.context().newPage();
        page1.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");
        assertThat(locator).isVisible();


        com.microsoft.playwright.BrowserContext browserContext1 = browser.newContext();
        Page page2 = browserContext1.newPage();
        page2.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");
        //assertThat(locator).not().isVisible();


        browser.close();
        playwright.close();
    }
}
