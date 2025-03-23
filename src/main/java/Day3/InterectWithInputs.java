package Day3;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InterectWithInputs {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        page.locator("input#user-message").type("Hello World!");
        page.locator("id=showInput").click();
        String message = page.locator("id=message").textContent();
        System.out.println(message);

        assertThat(page.locator("id=message")).hasText("Hello World!");
        //playwright.close();

        //Type vs fill

        page.navigate("https://www.lambdatest.com/selenium-playground/generate-file-to-download-demo");
        page.locator("id=textbox").type("Hello textdox jshdbcjhbsdhcbhsjdbckjhdvcjkhdbcjhdbvcjkhdbckjhdbckjhdvcjkhbddhcgvekhdgcvku cuedvyuk");
        page.locator("id=textbox").fill("Hello textdox ");



        page.navigate("https://letcode.in/edit");
        //to get the input text filled value
        System.out.println(page.locator("#getMe").inputValue());
        //toget the placeholder name for the input text field using attribute
        System.out.println(page.locator("#fullName").getAttribute("placeholder"));

        Locator fullnameLocator = page.locator("#fullName");
        assertThat(fullnameLocator).hasAttribute("Placeholder", "Enter first & last name");

        //checkbox demo
        //check if the checkbox is initially unchecked
        page.navigate("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        assertThat(page.locator("#isAgeSelected")).not().isChecked();
        Locator checkBoxLocator = page.locator("#isAgeSelected");
        checkBoxLocator.check();
        assertThat(checkBoxLocator).isChecked();


    }


}
