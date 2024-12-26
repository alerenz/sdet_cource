import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.commands.As;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;


public class MainTest {
    private static final Logger logger = LogManager.getLogger(MainTest.class);

    @BeforeClass
    void initBeforeClass() {
        logger.info("Открытие сайта");
        Configuration.baseUrl = "https://ulstu.ru";
        Configuration.browserSize = "1536x861";
        Configuration.timeout = 30000;
    }

    @Test(description = "Open Sports page")
    public void openSportsPage(){
        logger.info("Главная страница");
        open("/");
        page(MainPage.class)
                .goToSportPage()
                .sportPageIsOpen();

    }
}
