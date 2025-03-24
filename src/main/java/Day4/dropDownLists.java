package Day4;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.SelectOption;

import java.sql.SQLOutput;
import java.util.List;

import static  com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class dropDownLists {

    public static void main(String[] args) {
        String selectURL = "https://www.lambdatest.com/selenium-playground/select-dropdown-demo";
        String jqueryURL = "https://www.lambdatest.com/selenium-playground/jquery-dropdown-search-demo";
        Page page = Playwright.create().chromium().launch( new BrowserType.LaunchOptions().setHeadless(false)).newPage();

        //launch browser
        page.navigate(selectURL);
        Locator dayLocator = page.locator("#select-demo");

        //select by value
        dayLocator.selectOption("Wednesday");
        assertThat(page.locator("p.selected-value")).containsText("Wednesday");

        //select by label
        dayLocator.selectOption("Friday");
        assertThat(page.locator("p.selected-value")).containsText("Friday");

        //select by index
        dayLocator.selectOption( new SelectOption().setIndex(2));
        assertThat(page.locator("p.selected-value")).containsText("Monday");

        //select multiple
        Locator statesLocator = page.locator("#multi-select");
        statesLocator.selectOption(new String[]{"New Jersey", "Texas"});
        Locator options = statesLocator.locator("Option");
        System.out.println(options.count());
        List<String> allInnerText = options.allInnerTexts();

        allInnerText.forEach(option -> System.out.println(option));


        //select jquery

        page.navigate(jqueryURL);
        Locator country = page.locator("//*[@id=\"__next\"]/div/section[2]/div/div/div/div[1]/div[2]/span/span[1]/span");
        country.click();
        Locator selectSingleCountryFromList = page.locator("//*[@id=\"select2-country-results\"]", new Page.LocatorOptions().setHasText("Denmark"));
        selectSingleCountryFromList.click();

        Locator files = page.locator("//*[@id=\"files\"]");
        files.selectOption("Ruby");
    }
}
