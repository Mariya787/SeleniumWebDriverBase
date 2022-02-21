package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;


public class SeleniumWebDriverManagerTests {

    @Test
    public void WebDriverManagerTest() throws InterruptedException {
        WebDriver driver = null;
        String browser = System.getProperty("browser");

        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(browser.equals("opera")){
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        }else if(browser.equals("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.get("https://the-internet.herokuapp.com");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.quit();
    }

}
