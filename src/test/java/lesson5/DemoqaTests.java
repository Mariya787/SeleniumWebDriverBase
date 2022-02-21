package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class DemoqaTests {

    private WebDriver driver;


    @BeforeEach
    public void setUp() {

        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("opera")) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void checkCurrentUrl() throws InterruptedException {

        driver.getCurrentUrl();
        Assertions.assertEquals("https://demoqa.com/automation-practice-form", driver.getCurrentUrl());
        Thread.sleep(2000);
    }

    @Test
    public void checkTitle() {
        driver.getTitle();
        Assertions.assertEquals("ToolsQA", driver.getTitle());

    }

    @Test
    public void completeFormTest() throws InterruptedException {

        String firstName = "Mariya";
        String lastName = "Korzhavina";
        String email = "m.s.korzhavina@gmail.com";
        String mobile = "9529051494";
        String address = "Kolcovo 36";


        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);

        driver.findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[2]")).click();

        driver.findElement(By.id("userNumber")).sendKeys(mobile);

        driver.findElement(By.id("dateOfBirthInput")).click();
        WebElement year = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]//select[@class = 'react-datepicker__year-select']"));
        Select selectYear = new Select(year);
        WebElement month = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]//select[@class = 'react-datepicker__month-select']"));
        Select selectMonth = new Select(month);
        selectYear.selectByValue("1987");
        selectMonth.selectByValue("6");

        driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]//div[@aria-label='Choose Wednesday, July 1st, 1987']")).click();

        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", subjects);
        Thread.sleep(2000);
        subjects.sendKeys("English");
        subjects.sendKeys(Keys.ENTER);
        subjects.sendKeys("Maths");
        subjects.sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]/label")).click();
        driver.findElement(By.cssSelector("[for='hobbies-checkbox-3']")).click();

        driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\Маша\\IdeaProjects\\SeleniumWebDriverBase\\src\\picture.jpg");

        driver.findElement(By.id("currentAddress")).sendKeys(address);

        Thread.sleep(2000);

        WebElement state = driver.findElement(By.id("react-select-3-input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", state);
        Thread.sleep(2000);
        state.sendKeys("NCR");
        state.sendKeys(Keys.ENTER);

        WebElement city = driver.findElement(By.id("react-select-4-input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", city);
        Thread.sleep(2000);
        city.sendKeys("Delhi");
        city.sendKeys(Keys.ENTER);

        Thread.sleep(2000);

        driver.findElement(By.id("submit")).click();

        Thread.sleep(5000);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
