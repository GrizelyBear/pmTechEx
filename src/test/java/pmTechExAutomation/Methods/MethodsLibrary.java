package pmTechExAutomation.Methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MethodsLibrary {
    public BufferedReader jsonFileReader = null;
    public JsonObject jsonObj = null;

    /* Method to onfigure the Web Driver (Google Chrome) where the test scripts will be ran */
    public WebDriver setupWebDriverConfig(WebDriver driver) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--window-size=1440,900");

        driver = new ChromeDriver(chromeOptions);

        driver.manage().window().maximize();

        return driver;
    }

    /* Method to open the parser to read the Test Data JSON file */
    public BufferedReader parseJsonFile(String fileName, String fileDir) {
        try {

            /* If user does not provide any file path, get environment vars. */
            if(fileDir.isEmpty() || fileDir.equals("")){
                fileDir = System.getProperty("user.dir");
            }

            jsonFileReader = new BufferedReader (new FileReader(fileDir +
                "/src/test/java/pmTechExAutomation/TestData/" + fileName));
            jsonObj = new Gson().fromJson(jsonFileReader, JsonObject.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return jsonFileReader;
    }

    /*
     * Method to find the value of a JSON path and return it as a string
     */
    public String findJsonStringValue(String query){
        return JsonPath.read(jsonObj.toString(), query).toString();
    }

    /*
     * Method to switch to a new tab.
     */
    public void switchToNewTab(WebDriver driver, String currentTab){
        ArrayList<String> newOpenedTab = new ArrayList<String>(driver.getWindowHandles());

        newOpenedTab.remove(currentTab);
        driver.switchTo().window(newOpenedTab.get(0));
    }

    /* 
     * Method to wait until a web element is visible
     */
    public WebElement waitUntilElementVisible(WebDriver driver, WebDriverWait wait, String locType, String input) {
        WebElement element = null;

        switch(locType.toLowerCase()){
            case "id":
                element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(
                    driver.findElement(By.id(input)))));
                break;
            case "xpath":
                element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(
                    driver.findElement(By.xpath(input)))));
                break;
            default:
                System.out.println("Identifier [ " + locType + " ] is not valid!");
                break;
        }

        return element;
    }

    /* 
     * Method to pause the test for a specified duration
     */
    public void testPause(long duration){
        try {
            Thread.sleep(duration);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}