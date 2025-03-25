package Day5;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.options.ScreenshotCaret;

import java.nio.file.Paths;
import java.util.Arrays;

public class LearnScreenshots {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch( new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");
        System.out.println(page.title());

        //Screenshots
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        page.screenshot(screenshotOptions.setPath(Paths.get("./snaps/src.png")));

        //full page screenshot
        page.screenshot(screenshotOptions.setFullPage(true).setPath(Paths.get("./snaps/fullpage.png")));

        // locator screenshot
        Locator buttonLocator = page.locator("//*[@id=\"header\"]/nav/div/div/div[2]/div/div/div[2]/div[1]/button");
        buttonLocator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snaps/btn.png")));

        Locator header = page.locator("#header");
        header.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snaps/header.png")));

        //Masking Locator

        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        Locator input = page.locator("input#user-message");
        input.type("typetype");
        input.scrollIntoViewIfNeeded();
        page.screenshot(new ScreenshotOptions().setPath(Paths.get("./snaps/input.png"))
                .setFullPage(false)
                .setMask(Arrays.asList(input))
                .setMaskColor("000000")
        );

        //caret hiding
        input.click();
        page.screenshot( new ScreenshotOptions().setCaret(ScreenshotCaret.HIDE)
                .setPath(Paths.get("./snaps/caretHide.png"))
        );

        //closing page/browser/playwright
        page.close();
        browser.close();
        playwright.close();
    }
}
