import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FindElementsTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver(); // Инициализируем драйвер
        driver.get("https://ilcarro.web.app"); // Открываем ссылку на домашнюю страницу сайта, который тестируем
        //driver.manage().window().setPosition(new Point(2500, 0)); // Подвинуть окно браузера на 2500 пикселей вправо, чтобы он запускался на втором мониторе
        driver.manage().window().maximize(); // На весь экран развернуть браузер
        // Неявное ожидание локатора. Если элемент появится до истечения времени, Selenium сразу продолжит выполнение, не дожидаясь окончания таймера.
        // Устанавливает глобальное ожидание на все элементы, которые вы пытаетесь найти с помощью методов findElement или findElements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testFindElementsTagName2() {
        //* -------------------- Локатор с тегом <h1> -------------------- *//
        driver.findElement(By.cssSelector("h1")); // Альтернативный способ
        WebElement h1 = driver.findElement(By.tagName("h1"));
        System.out.println("Заголовок h1 имеет текст: " + h1.getText());

        //* -------------------- Локатор с тегом <a> (логотип) -------------------- *//
        WebElement a = driver.findElement(By.tagName("a"));
        System.out.println("Текст элемента с тегом <a>: [" + a.getText() + "] -> текста нет, потому что это - картинка");
        System.out.println("Размер элемента с тегом <a>: " + a.getSize()); //? Элементов несколько, а первый - картинка. Поэтому у неё нет текста, но есть разрешение.

        //* -------------------- Локатор с тегом <img> (изображение внутри <a>) -------------------- *//
        WebElement img = driver.findElement(By.tagName("img"));
        String imgSrc = img.getAttribute("src"); // Получить ссылку на изображение
        System.out.println("Ссылка <src> на изображение <img>: " + imgSrc);

        //* -------------------- Массив элементов с тегом <a> -------------------- *//
        List<WebElement> elements_a = driver.findElements(By.tagName("a"));
        System.out.println("Размер массива элементов с тегом <a>: [" + elements_a.size() + "]");

        // Проверка, что элементов достаточно
        if (elements_a.size() > 2) {
            WebElement thirdElement = elements_a.get(2); // Получаем 3-й элемент (индекс 2)
            System.out.println("Текст 3-го элемента с тегом <a> в массиве: " + thirdElement.getText());
            System.out.println("Аттрибут <href>: " + thirdElement.getAttribute("href")); // Для получения ссылки объекта
        } else {
            System.out.println("Список содержит менее 3 элементов.");
        }

        //* -------------------- Перебор всех элементов <a> -------------------- *//
        for (WebElement element : elements_a) {
            System.out.println("Элемент [" + element.getText() + "] имеет атрибут <href>: " + element.getAttribute("href")); // Получаем ссылку и текст элемента
        }
    }

    @Test
    public void byID() {
        //! 📌 Стратегия поиска по id - это самая успешная стратегия.
        //? By.id
        //! #value
        //* #city
        driver.findElement(By.id("city"));
        driver.findElement(By.cssSelector("#city"));
        driver.findElement(By.xpath("//*[@id='city']")); // дольше, потому что не указано среди каких именно элементов ищем
        driver.findElement(By.cssSelector("input#city"));
        driver.findElement(By.xpath("//input[@id='city']")); // хоть #city и так уникален, но такое уточнение сделает поиск ещё быстрее
    }

    @Test
    public void byClassName() {
        //? By.className
        //! .value
        //* .telephone
        WebElement telephone1 = driver.findElement(By.className("telephone"));
        WebElement telephone2 = driver.findElement(By.cssSelector(".telephone"));
        System.out.println("telephone1: " + telephone1.getText() + "\ntelephone2: " + telephone2.getText());
    }

    @Test
    public void byLinkPartialLinkText() {
        //? By.linkText
        //! Ищет элементы, у которых точное совпадение текста.
        //* Далее по xPath, потому что CSS-селекторы не поддерживают поиск по тексту внутри элемента.
        WebElement letTheCarWork = driver.findElement(By.linkText("Let the car work"));
        System.out.println("Текст элемента 'Let the car work' в 'header': " + letTheCarWork.getText());

        WebElement search = driver.findElement(By.linkText("Search"));
        System.out.println("Текст элемента 'Search' в 'header': " + search.getText());

        //? By.partialLinkText
        //! Ищет элементы, у которых частичное совпадение текста.
        WebElement partialLinkText = driver.findElement(By.partialLinkText("work"));
        System.out.println("Текст элемента 'By.partialLinkText('work')': " + partialLinkText.getText());
        System.out.println("Размер элемента 'By.partialLinkText('work')': " + partialLinkText.getSize() + " пикселей");

        //? Посчитаем сколько элементов таких на странице в целом
        List<WebElement> work = driver.findElements(By.partialLinkText("work"));
        System.out.println("Количество элементов по частичному совпадению с 'work': " + work.size() + " шт.");
    }

    @Test
    public void chooseFasterMethod() {
        //* Ищем уникальный локатор
        driver.findElement(By.cssSelector(".logo")); //! 5 элементов, не подходит
        driver.findElement(By.cssSelector("div.header .logo")); //* 1 уникальный элемент, 🔥 Лучший выбор

        //! 🔥 Второй вариант проигрывает первому, потому что требуется сохранять все найденные элементы в коллекцию и потом обращаться к ним по индексу
        List<WebElement> logo = driver.findElements(By.className("logo"));//* 5 элементов, выбираем нужный
        logo.get(2).click();
    }

    @Test
    public void getAttributesTests() {
        //* 🔍 Получаем аттрибуты искомого элемента
        WebElement city = driver.findElement(By.id("city"));
        System.out.println(city.getSize()); //* Размер инпута (396, 46)
        System.out.println(city.getAttribute("type"));
        System.out.println(city.getAttribute("id"));
        System.out.println(city.getAttribute("formcontrolname"));
        System.out.println(city.getAttribute("ng-reflect-label"));
        System.out.println(city.getAttribute("class"));
        System.out.println(city.getAttribute("autocomplete"));
    }

    @Test
    public void xPathToCSConversion() {
        //! если нашли пару ng-reflect-name="city" то делаем из неё cssSelector [ng-reflect-name='city']
        //! [attr="value"] // Среда не любит двойные кавычки
        //* Находит элементы, у которых ng-reflect-name='city'. В нашем случае это input
        WebElement city = driver.findElement(By.cssSelector("[ng-reflect-name='city']"));
        System.out.println("Размер инпута поля 'city': " + city.getSize() + " пикселей");
    }

    @Test
    public void findElementsByExactAndPartialText() {
        //* 🔍 CSS-селекторы
        driver.findElement(By.cssSelector("#city")); // * Эквивалентно By.id("city"), потому что '#' обозначает ID
        driver.findElement(By.cssSelector("input#city")); // * Точный поиск элемента <input> с id='city'
        driver.findElement(By.cssSelector("input[id='city']")); // * Точное совпадение по атрибуту id='city'
        driver.findElement(By.cssSelector("input[id^='cit']")); // * Выбирает <input>, где id начинается с 'cit'
        driver.findElement(By.cssSelector("input[id$='ty']")); // * Выбирает <input>, где id заканчивается на 'ty'
        driver.findElement(By.cssSelector("input[id*='it']")); // * Выбирает <input>, где id содержит 'it' в любом месте

        //* 🔍 XPath-селекторы
        driver.findElement(By.xpath("//a[text()=' Let the car work ']")); //* Полное совпадение текста внутри <a> (учитывая пробелы)
        driver.findElement(By.xpath("//a[normalize-space(text())='Let the car work']")); //* Полное совпадение текста внутри <a> (игнорируя лишние пробелы в начале и в конце)
        //? Если нужен частичный поиск, лучше использовать contains()
        driver.findElement(By.xpath("//a[.=' Let the car work ']")); //* Аналогично предыдущему, '.' вместо text() – сокращенная запись
        driver.findElement(By.xpath("//a[contains(., ' Let the car work ')]")); //* содержит " Let the car work " (учитывает пробелы)
        driver.findElement(By.xpath("//a[contains(text(), ' Let the car work ')]")); //* содержит " Let the car work " (учитывает пробелы)
        driver.findElement(By.xpath("//a[contains(text(), 'work')]")); //* содержит "work" (гибче, чем полное совпадение), но таких совпадений может быть уже несколько
        driver.findElement(By.xpath("(//a[contains(text(), 'work')])[1]")); //* содержащий "work" (если таких элементов несколько)
        driver.findElement(By.xpath("//a[starts-with(text(), ' Let')]")); //* начинается с ' Let'
        driver.findElement(By.xpath("//a[substring(text(), string-length(text()) - string-length('work') + 1) = 'work']")); //* XPath не имеет функции ends-with(), но можно использовать substring() для поиска элемента, который заканчивается на что-то
    }

    @Test
    public void findElementsByXpath() {
        //? By.xpath
        //! //*[@attr='value'] (* значит искать любые элементы, место * поставить div и он будет искать только среди div)
        //! //*[contains(@attr,'value')] --> второй вариант поиска
        //! //*[contains(@class,'value') and contains(text(),'value')] --> третий вариант поиска
        //! //div[@class='someClass'] ищет div элемент с классом 'someClass'
        //! //*[@class='someClass'] ищет любой элемент с классом 'someClass'
        //! h1 -> //h1
        driver.findElement(By.xpath("//h1"));

        //!   id -> //*[@id='value']
        //* #city --> //*[@id='city']
        driver.findElement(By.xpath("//*[@id='city']"));

        //! //a[text()=' Let the car work ']
        //! //*[contains(@attr,'value')]
        driver.findElement(By.xpath("//a[text()=' Let the car work ']"));//* точное совпадение
        driver.findElement(By.xpath("//a[.=' Let the car work ']")); //* точное совпадение
        driver.findElement(By.xpath("//a[contains(text(),'work')]")); //* частичное совпадение
        driver.findElement(By.xpath("(//a[contains(text(),'work')])[1]")); //*  частичное совпадение (первый элемент на странице)
        driver.findElement(By.xpath("//a[starts-with(text(),'Let the car')]")); //* начинается с Let the car
    }

    @Test
    public void telephoneTests() {
        //! class -> //*[@class='value']
        //* .telephone -> //*[@class='telephone']
        driver.findElement(By.className("telephone"));
        driver.findElement(By.cssSelector(".telephone"));
        driver.findElement(By.cssSelector("address.address-container .telephone"));
        driver.findElement(By.xpath("//*[@class='telephone']"));
        driver.findElement(By.xpath("//a[.='866-720-5721']"));
        driver.findElement(By.xpath("//a[text()='866-720-5721']"));
        driver.findElement(By.xpath("//a[contains(text(),'-720-')]"));
        driver.findElement(By.xpath("//a[starts-with(text(),'866-72')]"));
        driver.findElement(By.linkText("866-720-5721"));
        driver.findElement(By.partialLinkText("-720-"));
        driver.findElement(By.xpath("//a[@class='telephone' and text()='866-720-5721']"));
        driver.findElement(By.xpath("//a[contains(@class, 'tele') and contains(text(),'-720-')]"));
    }

    @AfterMethod(enabled = true) // включение или отключения закрытия браузера после тестов
    public void tearDown() {
        driver.quit();
    }
}
