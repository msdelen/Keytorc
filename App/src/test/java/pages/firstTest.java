package pages;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class firstTest {
    public WebDriver driver;
    public WebDriverWait wait;

    String baseURL = "http://www.n11.com/";


    @Test
    public void firstTest() {

    }

    public firstTest(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
}