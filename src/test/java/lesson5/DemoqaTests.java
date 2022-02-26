package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class DemoqaTests {

    private WebDriver driver;
    private File file;


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

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        String gender = "Female";
        String mobile = "9529061505";
        String birthday = "01 July,1987";
        String address = "Kolcovo 36";
        String subjectFirst = "English";
        String subjectSecond = "Maths";
        String hobbyFirst = "Reading";
        String hobbySecond = "Music";
        String picture = "picture.jpg";
        String studentsState = "NCR";
        String studentsCity = "Delhi";


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
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", subjects);
        subjects.sendKeys(subjectFirst);
        subjects.sendKeys(Keys.ENTER);
        subjects.sendKeys(subjectSecond);
        subjects.sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]/label")).click();
        driver.findElement(By.cssSelector("[for='hobbies-checkbox-3']")).click();

        file = new File("src/picture.jpg");
        WebElement selectPictureButton = driver.findElement(By.id("uploadPicture"));
        selectPictureButton.sendKeys(file.getAbsolutePath());

        driver.findElement(By.id("currentAddress")).sendKeys(address);

        WebElement state = driver.findElement(By.id("react-select-3-input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", state);
        state.sendKeys(studentsState);
        state.sendKeys(Keys.ENTER);

        WebElement city = driver.findElement(By.id("react-select-4-input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", city);
        city.sendKeys(studentsCity);
        city.sendKeys(Keys.ENTER);

        WebElement submitButton = driver.findElement(By.id("submit"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(driver.findElement(By.id("example-modal-sizes-title-lg")).getText()).isEqualTo("Thanks for submitting the form");
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText()).isEqualTo(firstName + " " + lastName);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[2]/td[2]")).getText()).isEqualTo(email);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[3]/td[2]")).getText()).isEqualTo(gender);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[4]/td[2]")).getText()).isEqualTo(mobile);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[5]/td[2]")).getText()).isEqualTo(birthday);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[6]/td[2]")).getText()).isEqualTo(subjectFirst + ", " + subjectSecond);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[7]/td[2]")).getText()).isEqualTo(hobbyFirst + ", " + hobbySecond);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[8]/td[2]")).getText()).isEqualTo(picture);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[9]/td[2]")).getText()).isEqualTo(address);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[10]/td[2]")).getText()).isEqualTo(studentsState + " " + studentsCity);

        softAssert.assertAll();

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
