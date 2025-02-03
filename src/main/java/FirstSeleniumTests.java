import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class FirstSeleniumTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(2200,0)); // Сдвигает окно
        driver.get("https://www.google.com/"); // Очищает историю
        //driver.navigate().to("https://www.google.com/page3"); // Сохраняет историю
        //driver.navigate().back(); // Назад
        //driver.navigate().forward(); // Вперёд
        //driver.navigate().refresh(); // Обновляет страницу
        driver.manage().window().maximize(); // Разворачивает на весь экран
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Неявное ожидание
        System.out.println("Step 1");
    }

    @Test
    public void FirstSeleniumTest() {
        System.out.println("Step 2");
    }

    @AfterMethod(enabled = true)
    public void tearDown() {
        driver.quit();
        System.out.println("Step 3");
    }
}
