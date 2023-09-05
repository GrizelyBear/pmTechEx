package pmTechExAutomation.TestCases;

import java.io.BufferedReader;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pmTechExAutomation.Resources.JsonPathsLibrary;
import pmTechExAutomation.Resources.LocatorLibrary;
import pmTechExAutomation.Resources.LocatorLibrary.Step;
import pmTechExAutomation.Resources.LocatorLibrary.StepStatus;
import pmTechExAutomation.Resources.LocatorLibrary.ErrorType;
import pmTechExAutomation.Methods.MethodsLibrary;


public class AccountActivationRegression {
    public WebDriver driver = null;
    public BufferedReader fileReader;
    public WebDriverWait wait;
    public long pageTimeout = 90;

    SoftAssert softAssert = new SoftAssert();

    public String testURL = "";
    public String testEmailAddress = "";
    public String testPassword = "";
    public String testBusinessType = "";

    public String defaultBusinessHandle = "";

    static MethodsLibrary methods = new MethodsLibrary();

    public LocatorLibrary locatorVars = new LocatorLibrary();
    public LocatorLibrary.LogInPageVariables loginVars = locatorVars.new LogInPageVariables();
    public LocatorLibrary.AccountActivationVariables accActVars = locatorVars.new AccountActivationVariables();
    public LocatorLibrary.OtherPageVariables otherVars = locatorVars.new OtherPageVariables();

    public JsonPathsLibrary jsonVars = new JsonPathsLibrary();

    @BeforeClass
    @Parameters ({"emailAddress", "password", "businessType"})
    public void setup(String emailAddress, String password, String businessType) {
        fileReader = methods.parseJsonFile("AccountActivationRegressionTestData.json", "");
        driver = methods.setupWebDriverConfig(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(pageTimeout));

        testURL = methods.findJsonStringValue(jsonVars.testURLPath);
        testEmailAddress = emailAddress;
        testPassword = password;
        testBusinessType = businessType;
    }

    @Test
    public void verifyUserLoginIn() {
        driver.get(testURL);

        /* Assert that the user is on the correct log-in page by checking the header. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            loginVars.logInHeaderXpath).isDisplayed());
        
        /* Log-in using the email and password provided. */
        methods.waitUntilElementVisible(driver, wait, "xpath",
            loginVars.emailAddressInputXpath).sendKeys(testEmailAddress);
        methods.waitUntilElementVisible(driver, wait, "xpath",
            loginVars.passwordInputXpath).sendKeys(testPassword);
        methods.waitUntilElementVisible(driver, wait, "xpath", loginVars.submitBtnXpath).click();
    }

    @Test (dependsOnMethods = {"verifyUserLoginIn"})
    public void verifyAccountActivationFirstStep() {
        methods.testPause(15000); // Temporary workaround to wait for the elements to load.
        
        /* Assert that the user has been properly redirected to Step 1 of the Account Activation module. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step1HeaderXpath).isDisplayed());

        /* Assert that the Roadmap Status of this step is "In progress." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.FIRST)).getText(),
            StepStatus.IN_PROGRESS.returnStepStatusLabel());

        /* Check links in the page. */
        verifyFirstStepDocumentsLink();
        verifyFirstStepRestrictedBusinessesLink();

        /* Proceed to the next step. */
        driver.findElement(By.xpath(accActVars.nextBtnXpath)).click();
    }

    @Test (dependsOnMethods = {"verifyAccountActivationFirstStep"})
    public void verifyAccountActivationSecondStep() {
        methods.testPause(15000); // Temporary workaround to wait for the elements to load.
        
        /* Assert that the user has been properly redirected to Step 2 of the Account Activation module. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step2HeaderXpath).isDisplayed());

        /* Assert that the Roadmap Status of this step is "In progress." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.SECOND)).getText(),
            StepStatus.IN_PROGRESS.returnStepStatusLabel());

        /* Assert that the Roadmap Status of the previous step is "Completed." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.FIRST)).getText(),
            StepStatus.COMPLETED.returnStepStatusLabel());
    }

    @Test (dependsOnMethods = {"verifyAccountActivationSecondStep"})
    public void verifyAccountActivationSecondStepInitialValues() {

        // Verify the initial value of the Business Store Name
        Assert.assertEquals(
            driver.findElement(By.id(accActVars.step2BusinessStoreNameID)).getAttribute("value"),
            methods.findJsonStringValue(jsonVars.signUpBusinessNamePath));

        // Verify if a default value has been set for the Business Handle
        defaultBusinessHandle = driver.findElement(By.id(accActVars.step2BusinessHandleID)).getAttribute("value");

        Assert.assertTrue(!defaultBusinessHandle.isEmpty());
    }

    @Test (dependsOnMethods = {"verifyAccountActivationSecondStepInitialValues"})
    public void verifyAccountActivationSecondStepErrorHandling() {
        verifySubmitOfBlankSecondStepForm();
        verifySubmitOfDefaultBusinessHandleSecondStepForm();
    }

    @AfterClass
    public void tearDown(){
        try {
            // driver.close();
            // driver.quit();
            fileReader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /* Methods below are specific for this test only. */
    
    public void verifyFirstStepDocumentsLink() {
        String step1Tab = driver.getWindowHandle();

        /* Verify if the Documents link opens the correct tab. */
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step1DocumentsLinkXpath).click();
        methods.switchToNewTab(driver, step1Tab);

        softAssert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            otherVars.requiredDocumentsPageHeaderXpath).isDisplayed());

        driver.close(); // Close new tab
        driver.switchTo().window(step1Tab); // Go back to original tab
    }

    public void verifyFirstStepRestrictedBusinessesLink() {
        String step1Tab = driver.getWindowHandle();

        /* Verify if the Restricted Businesses link opens the correct tab. */
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step1RestrictedBusinessesLinkXpath).click();
        methods.switchToNewTab(driver, step1Tab);

        softAssert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            otherVars.restrictedBusinessesPageHeaderXpath).isDisplayed());

        driver.close(); // Close new tab
        driver.switchTo().window(step1Tab); // Go back to original tab
    }

    public void verifySubmitOfBlankSecondStepForm() {

        /* Clear all pre-filled fields. */
        driver.findElement(By.id(accActVars.step2BusinessStoreNameID)).clear();
        driver.findElement(By.id(accActVars.step2BusinessHandleID)).clear();

        driver.findElement(By.xpath(accActVars.nextBtnXpath)).click();

        methods.testPause(15000);

        /* Check presence of error messages. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_STORE_NAME)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_CUSTOMER_SERVICE_NUM)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_ADDRESS_LINE1)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_CITY)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_REGION)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_ZIP_CODE)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_SIZE)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_INDUSTRY)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_PRODUCT_DESC)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_LINK)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_HANDLE)).isDisplayed());
    }

    public void verifySubmitOfDefaultBusinessHandleSecondStepForm() {
        
        /* Fill the Business Handle field with the default Business Handle */
        System.out.print("HELLO!!!!!!!!!!!");
        driver.findElement(By.id(accActVars.step2BusinessHandleID)).sendKeys(defaultBusinessHandle);

        driver.findElement(By.xpath(accActVars.nextBtnXpath)).click();

        methods.testPause(15000);

        /* Check presence of error messages. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.DEFAULT_BUSINESS_HANDLE)).isDisplayed());
    }
    
}

