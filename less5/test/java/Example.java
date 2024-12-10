import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;


public class Example {

    @BeforeClass
    void init(){
        Configuration.baseUrl = "https://ulstu.ru/";
        Configuration.browserSize = "1536x864";
        Configuration.timeout = 30000;
    }

    @Test
    public void test1(){
        open("https://ulstu.ru/");
        $(By.xpath("//*[contains(@class, 'btn-profile')]"))
                .shouldHave(text("Личный кабинет"))
                .click();
        $(By.xpath("//h3")).shouldHave(text("Вход")).shouldBe(visible);
        sleep(5000);

    }
}
