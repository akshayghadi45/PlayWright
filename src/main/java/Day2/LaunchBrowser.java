package Day2;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LaunchBrowser {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php");
        Locator myAccount = page.locator("//*[@id=\"widget-navbar-217834\"]/ul/li[6]/a");
        myAccount.hover();
        page.locator("//*[@id=\"widget-navbar-217834\"]/ul/li[6]/ul/li[1]/a/div/span").click();
        assertThat(page).hasTitle("Account Login");

        page.getByPlaceholder("E-Mail Address").type("akshayghadi@flash.co");
        page.getByPlaceholder("Password").type("T@hDud6vjQ3cEX9");
        page.locator("//*[@id=\"content\"]/div/div[2]/div/div/form/input").click();
        assertThat(page).hasTitle("My Account");
        page.close();
        browser.close();
        playwright.close();
    }
}
