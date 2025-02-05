import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class FindElementsHw extends TestBaseHw {

    @Test
    public void findElementsByCssSelector() {
        WebElement giftCardsElement = driver.findElement(By.cssSelector("li.inactive > a[href='/gift-cards']"));
        System.out.println("Элемент содержит следующий текст: " + giftCardsElement.getText());

        WebElement demoWebShopLogo = driver.findElement(By.cssSelector("img[alt='Tricentis Demo Web Shop']"));
        System.out.println("Размер логотипа: " + demoWebShopLogo.getSize() + " пикселей");

        WebElement registerElement = driver.findElement(By.cssSelector("a.ico-register"));
        System.out.println("Элемент register имеет следующую гиперссылку: " + registerElement.getAttribute("href"));

        WebElement imgElement1 = driver.findElement(By.cssSelector("img[alt='Picture of Build your own expensive computer']"));
        System.out.println("Элемент содержит следующий alt атрибут: " + imgElement1.getAttribute("alt"));

        WebElement elementFacebook = driver.findElement(By.cssSelector("li.facebook > a"));
        System.out.println("Элемент Facebook использует следующую цепочку шрифтов: " + elementFacebook.getCssValue("font-family"));

        WebElement topMenuElement = driver.findElement(By.cssSelector("ul.top-menu"));
        System.out.println("Элемент меню имеет следующий цвет и прозрачность: " + topMenuElement.getCssValue("color"));

        WebElement imgElement2 = driver.findElement(By.cssSelector("img[alt='Picture of $25 Virtual Gift Card']"));
        System.out.println("Элемент располагается на странице на следующей X,Y позиции" + imgElement2.getLocation());

        WebElement elementNivo = driver.findElement(By.cssSelector("a.nivo-control.active"));
        System.out.println("Элемент имеет размер: " + elementNivo.getSize() + " пикселей");

        WebElement elementCart = driver.findElement(By.cssSelector("li#topcartlink>a"));
        System.out.println("Элемент Корзина имеет следующую гиперссылку: " + elementCart.getAttribute("href"));

        WebElement elementPopularTags = driver.findElement(By.cssSelector(".block-popular-tags strong"));
        System.out.println("Шрифт элемента популярные теги имеет следующие свойства:  " + elementPopularTags.getCssValue("font"));
    }
}