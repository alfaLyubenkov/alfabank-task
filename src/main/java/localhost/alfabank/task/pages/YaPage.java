package localhost.alfabank.task.pages;

import ch.lambdaj.function.convert.Converter;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static ch.lambdaj.Lambda.convert;

/**
 * Created by lyubenkov on 24.03.2016.
 */
public class YaPage extends PageObject {
    @FindBy(name="text")
    private WebElementFacade searchField;

    @FindBy(className="button suggest2-form__button button_theme_websearch button_size_xl i-bem button_js_inited")
    private WebElementFacade findButton;

    public void enterQuery(String keyword) {
        searchField.type(keyword);
    }

    public void lookupQuery() {
        findButton.click();
    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.tagName("ol"));
        List<WebElement> results = definitionList.findElements(By.tagName("li"));
        return convert(results, toStrings());
    }

    private Converter<WebElement, String> toStrings() {
        return new Converter<WebElement, String>() {
            public String convert(WebElement from) {
                return from.getText();
            }
        };
    }
}
