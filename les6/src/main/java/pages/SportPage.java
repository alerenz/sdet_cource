package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class SportPage {


    @FindBy(xpath = "//h1")
    private SelenideElement pageHeader;

    public SportPage sportPageIsOpen(){
        pageHeader
                .shouldHave(text("Спорт"))
                .shouldBe(visible);
        return this;
    }

}
