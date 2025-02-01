import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FindElements {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ilcarro.web.app");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void FindElementsByTagName() {
        WebElement h1 = driver.findElement(By.tagName("h1"));
        System.out.println("Заголовок элемента h1 имеет текст: " + h1.getText());

        WebElement a = driver.findElement(By.tagName("a"));
        System.out.println("Элемент <a> имеет размер: " + a.getSize() + " пикселей");

        WebElement img = driver.findElement(By.tagName("img"));
        System.out.println("Ссылка на изображение: " + img.getAttribute("src"));

        //? Массив элементов с тегом <a>
        List<WebElement> elements_a = driver.findElements(By.tagName("a"));
        System.out.println("Количество найденных элементов с тегом <a>: " + elements_a.size());
        System.out.println("Текст в элементе с тегом <a> номер 2: " + elements_a.get(2).getText());
    }

    @Test
    public void FindElementsByLocator(){
        //? By.id
        //! #value
        //* #city
        WebElement city = driver.findElement(By.id("city"));
        driver.findElement(By.cssSelector("input#city"));
        driver.findElement(By.xpath("//input[@id='city']"));
        driver.findElement(By.xpath("//*[@id='city']")); //поиск дольше
        //System.out.println(city.getLocation());

        driver.findElement(By.cssSelector("[ng-reflect-range-mode='true']"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
