package Day11;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Alerts {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch( new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
        Locator buttons = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Click Me"));

        /// Alert with single ok button that is "OK"
        page.onceDialog(alert->{
            System.out.println(alert.message());
            alert.accept();
        });
        buttons.first().click();

        /// Alert with two button that is "OK" and "Cancel"

        page.onceDialog(alert->{
            System.out.println(alert.message());
            alert.dismiss();
        });
        buttons.nth(1).click();
        assertThat(page.locator("#confirm-demo")).containsText("You pressed Cancel!");

        /// Alert with two buttons that is "OK" and "Cancel" and a prompt box
        page.onceDialog(alert->{
            System.out.println(alert.message());
            System.out.println(alert.defaultValue());
            alert.accept("Something");
        });
        buttons.nth(2).click();
        assertThat(page.locator("#prompt-demo")).hasText("You have entered 'Something' !");

        /// HTTP authentication dialog box (not a JS alert) => we need to use browser context

        BrowserContext newBrowserContext = browser.newContext(
                new Browser.NewContextOptions()
                        .setHttpCredentials("admin", "admin")
        );
        Page page1 = newBrowserContext.newPage();
        page1.navigate("https://the-internet.herokuapp.com/basic_auth");

        page1.waitForTimeout(2000);


        ///closing
        playwright.close();




    }
}
