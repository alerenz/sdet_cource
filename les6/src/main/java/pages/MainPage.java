package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;


public class MainPage {

//    Скопировала путь XPath из панели разработчика, так как с помощью ключевых слов contains, starts-with
//    не находились данные ссылки
    @FindBy(xpath = "//*[@id=\"ulstu-horizontal-multilevel-menu\"]/li[5]/a")
    private SelenideElement navItemStudentsLife;

    @FindBy(xpath = "//*[@id=\"ulstu-horizontal-multilevel-menu\"]/li[5]/div/ul/li[8]/a")
    private SelenideElement linkSport;


    public SportPage goToSportPage(){
        navItemStudentsLife
                .shouldBe(visible)
                .shouldHave(text("Студенческая жизнь"))
                .hover();
        linkSport
                .shouldHave(text("Спорт"))
                .click();
        return page(SportPage.class);
    }
}
