package Day6;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.Browser.NewContextOptions;

import java.nio.file.Paths;
import java.util.*;

public class Codegen {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
        BrowserContext context = browser.newContext(
                new NewContextOptions().setRecordVideoDir(Paths.get("Videos/")).setRecordVideoSize(1200,720)
        );
        Page page = context.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("My account")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("E-Mail Address")).fill("akshayghadi@flash.co");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("T@hDud6vjQ3cEX9");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Edit your account")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name*")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name*")).fill("Ghadi VKL");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();

        Locator successMessage = page.getByText("Success: Your account has been successfully updated");
        assertThat(successMessage).isVisible();
        Locator myAccount = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("My account"));
        myAccount.hover();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout").setExact(true)).click();
        Locator logoutSuccess = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Account Logout"));
        assertThat(logoutSuccess).isVisible();
        playwright.close();

        //mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://www.lambdatest.com/selenium-playground/simple-form-demo"
    }
}
