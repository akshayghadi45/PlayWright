package Day10;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import static  com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class PlaywrightDebugging {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate ("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");

        /// Playwright Inspecter in picture here we need to use the statement page.pause();
        ///this will cause the playwright debugger to come but the source code will not be visible, but we can execute the script step by step
        page.pause();

        Locator myAccount = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("My account"));
        myAccount.click();
        page.getByPlaceholder ("E-Mail Address")
                .fill("akshayghadi@flash.co");
        page.getByPlaceholder ("Password" )
                .fill("T@hDud6vjQ3cEX9");
        page. getByRole(AriaRole. BUTTON,new Page.GetByRoleOptions().setName ("Login")).click();

        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Edit your account information")).click();
        page.getByPlaceholder ("ALast Name").fill("C");
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName ("Continue")).click();
        Locator successMessage = page.getByText("Success: Your account has been successfully updated.");
        assertThat(successMessage).isVisible();
        myAccount.hover();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions ().setName ("Logout").setExact(true)).click();
        Locator logoutHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName ("Account Logout"));
        assertThat(logoutHeader). isVisible();

        page.close();
        browser.close();
        playwright.close();
    }
}
