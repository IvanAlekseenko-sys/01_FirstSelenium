import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class FindElementsXpathHw extends TestBaseHw {

    @Test
    public void findElementByXpath() {
        WebElement featuredProducts = driver.findElement(By.xpath("//strong[contains(text(),'Featured products')]"));
        System.out.println("Element contains text: " + featuredProducts.getText());

        WebElement voteButton = driver.findElement(By.xpath("//input[@value='Vote']"));
        System.out.println("Button Vote contains text: " + voteButton.getAttribute("value"));

        WebElement aboutUsElement = driver.findElement(By.xpath("//a[contains(text(),'About us')]"));
        System.out.println("Font size of the element ABOUT US is: " + aboutUsElement.getCssValue("font-size"));

        WebElement elementComputers = driver.findElement(By.xpath("(//ul[@class='top-menu']//a)[2]"));
        System.out.println("Element COMPUTERS contains following URL: " + elementComputers.getAttribute("href"));

        WebElement laptopImgElement = driver.findElement(By.xpath("//img[@alt='Picture of 14.1-inch Laptop']"));
        System.out.println("Img element has size: " + laptopImgElement.getSize() + " pixels");

        WebElement myAccElement = driver.findElement(By.xpath("//h3[contains(text(),'My account')]"));
        System.out.println("What case is used in the element: " + myAccElement.getCssValue("text-transform"));

        WebElement followUsElement = driver.findElement(By.xpath("//h3[contains(text(),'Follow us')]"));
        System.out.println("Font weight of the element is: " + followUsElement.getCssValue("font-weight"));

        WebElement imgTricentisElement = driver.findElement(By.xpath("//img[@alt='Tricentis Demo Web Shop']"));
        System.out.println("Location of the image element: " + imgTricentisElement.getLocation());

        WebElement subscribeElement = driver.findElement(By.xpath("//input[@value='Subscribe']"));
        System.out.println("Is SUBSCRIBE button enabled? " + subscribeElement.isEnabled());

        WebElement radio1Element = driver.findElement(By.xpath("(//input[@type='radio'])[1]"));
        System.out.println("Is 1st radio button selected? " + radio1Element.getAttribute("checked"));
        System.out.println("Same button, but after we click on it:");
        radio1Element.click();
        System.out.println("Is 1st radio button selected? " + radio1Element.getAttribute("checked"));
    }
}
