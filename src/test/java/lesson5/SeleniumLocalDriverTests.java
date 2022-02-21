package lesson5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class SeleniumLocalDriverTests {

    @Test
    public void OpenPageInChromeTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.quit();
    }
    @Test
    public void OpenPageInEdgeTest() throws InterruptedException {
        System.setProperty("webdriver.edge.driver", "C:\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        driver.get("https://the-internet.herokuapp.com");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.quit();
    }
    @Test
    public void OpenPageInOperaTest() throws InterruptedException {
        System.setProperty("webdriver.opera.driver", "C:\\operadriver.exe");
        WebDriver driver = new OperaDriver();

        driver.get("https://the-internet.herokuapp.com");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.quit();
    }
    @Test
    public void OpenPageWithParameterTest() throws InterruptedException {
        WebDriver driver = null;
        String browser = System.getProperty("browser");

        if(browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
            driver = new ChromeDriver();
        }else if(browser.equals("opera")){
            System.setProperty("webdriver.opera.driver", "C:\\operadriver.exe");
            driver = new OperaDriver();
        }

        driver.get("https://the-internet.herokuapp.com");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.quit();
    }

}