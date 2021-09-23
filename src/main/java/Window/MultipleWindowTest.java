package Window;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class MultipleWindowTest {

    public static WebDriver driver;

    public static void main(String[] args) {

        launch_chrome();
        OpenURL();

        JavascriptExecutor jse=(JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,300)");

        Openfacebook();

        Set<String> handles= driver.getWindowHandles();

        Iterator<String> it= handles.iterator();
        String parentWindowID=it.next();
        System.out.println("Parent WindowId:"+parentWindowID);

        String childWindowID= it.next();
        System.out.println("child WindowID"+childWindowID);

        driver.switchTo().window(childWindowID);
        driver.switchTo().window(parentWindowID);
        driver.get("https://www.youtube.com");
    }

    public static void launch_chrome(){
        System.setProperty("webdriver.chrome.driver","./src/main/resources/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }
    public static void OpenURL(){
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    public static void Openfacebook(){
        WebElement Facebook= driver.findElement(By.cssSelector("#social-icons > a:nth-child(2) > img"));
        Facebook.click();
    }
}
