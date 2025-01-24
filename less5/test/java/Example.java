import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Example {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(Example.class);

    @BeforeClass
    void init() {
        logger.info("Инициализация драйвера и ожидания");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        logger.info("Драйвер инициализирован успешно");
    }

    @Test
    public void test2() {
        logger.info("Открытие страницы https://ulstu.ru/");
        driver.get("https://ulstu.ru/");
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(@class, 'btn-profile')]")));
            Assert.assertTrue(button.getText().contains("Личный кабинет"),
                    "Кнопка не содержит текст 'Личный кабинет'");
            logger.info("Кнопка 'Личный кабинет' найдена");
            button.click();

            // ошибка возникает с данным тэгом. Тест не может найти данный тэг ни каким способом
//            WebElement heading = driver.findElement(By.xpath("//*[contains(@class, 'mb-1')]")));
//            WebElement heading = driver.findElement(By.xpath("//h3"))); 
            WebElement heading = driver.findElement(By.tagName("h3"));
            Assert.assertEquals(heading.getText(), "Вход", "Заголовок не совпадает с 'Вход'");
            logger.info("Заголовок 'Вход' найден");

        } catch (Exception e) {
            logger.error("Произошла ошибка во время выполнения теста", e);
            throw e;
        } finally {
            logger.info("Закрытие драйвера");
            if (driver != null) {
                driver.quit();
            }
            logger.info("Тест завершен");
        }
    }

    @AfterClass
    public void tearDown() {
        logger.info("Закрытие теста");
    }
}
