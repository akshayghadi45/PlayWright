package Day7;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LearnWindowHandling {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");
        System.out.println(page.title());

        /*
         // code for single pop up
        Page popUp = page.waitForPopup(()->{
            page.getByText("Follow On Twitter").click();
        });
        popUp.waitForLoadState();
        assertThat(popUp).hasTitle("LambdaTest (@lambdatesting) / X");
        System.out.println(popUp.title());

        */

        //code for multiple popups
        Page tabs = page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(p->p.context().pages().size()==3),()->{
            page.getByText("Follow All").click();
        });

        //tabs.waitForLoadState();
        List<Page> pages = tabs.context().pages();
        System.out.println(pages.size());
        pages.forEach( tab-> System.out.println(tab.title()+"/n"));

        Page fbPage = null;
        Page twitterPage = null;
        if(pages.get(0).title().endsWith("Twitter")){
            twitterPage = pages.get(1);
        }else{
            fbPage = pages.get(0);
        }

        System.out.println(twitterPage.url());
        playwright.close();
    }
}
