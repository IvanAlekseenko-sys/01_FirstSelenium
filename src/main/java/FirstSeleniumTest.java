import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstSeleniumTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver(); //Запускаем браузер
        driver.manage().window().maximize();
        driver.get("https://google.com"); //очищает историю
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // неявное ожидание
//        driver.navigate().to("https://amazon.com"); //сохраняет историю
//        driver.navigate().back();
//        driver.navigate().forward();
//        driver.navigate().refresh();
        System.out.println("Step 1");
    }

    @Test
    public void firstSeleniumTest() {
        System.out.println("Step 2");

    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Step 3");
        driver.quit();
    }
}
