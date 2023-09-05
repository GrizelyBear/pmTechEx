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
        // methods.testPause(15000); // Temporary workaround to wait for the elements to load.
        
        // /* Assert that the user has been properly redirected to Step 1 of the Account Activation module. */
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.step1HeaderXpath).isDisplayed());

        // /* Assert that the Roadmap Status of this step is "In progress." */
        // Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnStepRoadmapStatusXpath(Step.FIRST)).getText(),
        //     StepStatus.IN_PROGRESS.returnStepStatusLabel());

        // /* Check links in the page. */
        // verifyFirstStepDocumentsLink();
        // verifyFirstStepRestrictedBusinessesLink();

        // /* Proceed to the next step. */
        // driver.findElement(By.xpath(accActVars.nextBtnXpath)).click();
    }

    @Test (dependsOnMethods = {"verifyAccountActivationFirstStep"})
    public void verifyAccountActivationSecondStep() {
        // methods.testPause(15000); // Temporary workaround to wait for the elements to load.
        
        // /* Assert that the user has been properly redirected to Step 2 of the Account Activation module. */
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.step2HeaderXpath).isDisplayed());

        // /* Assert that the Roadmap Status of this step is "In progress." */
        // Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnStepRoadmapStatusXpath(Step.SECOND)).getText(),
        //     StepStatus.IN_PROGRESS.returnStepStatusLabel());

        // /* Assert that the Roadmap Status of the previous step is "Completed." */
        // Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnStepRoadmapStatusXpath(Step.FIRST)).getText(),
        //     StepStatus.COMPLETED.returnStepStatusLabel());

        // /* Assert the initial value of the Business Store Name. */
        // Assert.assertEquals(
        //     driver.findElement(By.id(accActVars.step2BusinessStoreNameID)).getAttribute("value"),
        //     methods.findJsonStringValue(jsonVars.signUpBusinessNamePath));

        // /* Assert that the Business Handle has a default value. */
        // defaultBusinessHandle = driver.findElement(By.id(accActVars.step2BusinessHandleID)).getAttribute("value");

        // Assert.assertTrue(!defaultBusinessHandle.isEmpty());
    }

    @Test (dependsOnMethods = {"verifyAccountActivationSecondStep"})
    public void verifyAccountActivationSecondStepErrorHandling() {
        // verifySubmitOfBlankSecondStepForm();

        // /* Assert that the Roadmap Status of this step is "With issues." */
        // Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnStepRoadmapStatusXpath(Step.SECOND)).getText(),
        //     StepStatus.WITH_ISSUES.returnStepStatusLabel());
        
        // verifySubmitOfDefaultBusinessHandleSecondStepForm();

        // /* Assert that the Roadmap Status of this step is "With issues." */
        // Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnStepRoadmapStatusXpath(Step.SECOND)).getText(),
        //     StepStatus.WITH_ISSUES.returnStepStatusLabel());
    }

    @Test (dependsOnMethods = {"verifyAccountActivationSecondStepErrorHandling"})
    public void verifyAccountActivationSecondStepSuccessfulSubmit() {
        // fillUpValidGeneralInformationForm();

        // /* Verify that no error messages are present. */
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_STORE_NAME)).isDisplayed()));
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_CUSTOMER_SERVICE_NUM)).isDisplayed()));
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_ADDRESS_LINE1)).isDisplayed()));
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_CITY)).isDisplayed()));
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_REGION)).isDisplayed()));
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_ZIP_CODE)).isDisplayed()));
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_SIZE)).isDisplayed()));
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_INDUSTRY)).isDisplayed()));
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_PRODUCT_DESC)).isDisplayed()));
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_LINK)).isDisplayed()));
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_HANDLE)).isDisplayed()));
        // Assert.assertTrue(!(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.DEFAULT_BUSINESS_HANDLE)).isDisplayed()));

        // driver.findElement(By.xpath(accActVars.nextBtnXpath)).click();
    }

    @Test (dependsOnMethods = {"verifyAccountActivationSecondStepSuccessfulSubmit"})
    public void verifyAccountActivationThirdStepChooseBusinessType() {
        // methods.testPause(15000); // Temporary workaround to wait for the elements to load.
        
        // /* Assert that the user has been properly redirected to Step 3 of the Account Activation module. */
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.step3HeaderXpath).isDisplayed());

        // /* Assert that the Roadmap Status of this step is "In progress." */
        // Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnStepRoadmapStatusXpath(Step.THIRD)).getText(),
        //     StepStatus.IN_PROGRESS.returnStepStatusLabel());

        // /* Assert that the Roadmap Status of the previous steps are "Completed." */
        // Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnStepRoadmapStatusXpath(Step.FIRST)).getText(),
        //     StepStatus.COMPLETED.returnStepStatusLabel());
        // Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnStepRoadmapStatusXpath(Step.SECOND)).getText(),
        //     StepStatus.COMPLETED.returnStepStatusLabel());

        // driver.findElement(By.xpath(accActVars.returnStep3BusinessTypeRadioButtonXpath(testBusinessType))).click();
        // driver.findElement(By.xpath(accActVars.nextBtnXpath)).click();
    }

    @Test (dependsOnMethods = {"verifyAccountActivationThirdStepChooseBusinessType"})
    public void verifyAccountActivationThirdStepBusinessFormInitialValues() {
        methods.testPause(15000); // Temporary workaround to wait for the elements to load.

        /* Assert the initial values of the Legal Name. */
        Assert.assertEquals(
            driver.findElement(By.id(accActVars.step3FirstNameID)).getAttribute("value"),
            methods.findJsonStringValue(jsonVars.signUpFirstNamePath));
        Assert.assertEquals(
            driver.findElement(By.id(accActVars.step3LastNameID)).getAttribute("value"),
            methods.findJsonStringValue(jsonVars.signUpLastNamePath));
    }

    @Test (dependsOnMethods = {"verifyAccountActivationThirdStepBusinessFormInitialValues"})
    public void verifyAccountActivationThirdStepBusinessFormErrorHandling() {
        verifySubmitOfBlankThirdStepForm();

        /* Assert that the Roadmap Status of this step is "With issues." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.THIRD)).getText(),
            StepStatus.WITH_ISSUES.returnStepStatusLabel());
    }

    @Test (dependsOnMethods = {"verifyAccountActivationThirdStepBusinessFormErrorHandling"})
    public void verifyAccountActivationThirdStepSuccessfulSubmit() {
        if (testBusinessType.equals("Individual")) {
            fillUpValidIndividualBusinessInformationForm();
        } 
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
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_INDUSTRY)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_PRODUCT_DESC)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_LINK)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_HANDLE)).isDisplayed());
    }

    public void verifySubmitOfDefaultBusinessHandleSecondStepForm() {
        
        /* Fill the Business Handle field with the default Business Handle */
        driver.findElement(By.id(accActVars.step2BusinessHandleID)).sendKeys(defaultBusinessHandle);

        driver.findElement(By.xpath(accActVars.nextBtnXpath)).click();

        methods.testPause(15000);

        /* Check presence of error messages. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.DEFAULT_BUSINESS_HANDLE)).isDisplayed());

        /* Revert back to blank state */
        driver.findElement(By.id(accActVars.step2BusinessHandleID)).clear();
    }

    public void fillUpValidGeneralInformationForm() {
        driver.findElement(By.id(accActVars.step2BusinessStoreNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validGenInfoBusinessNamePath));
        driver.findElement(By.id(accActVars.step2CustomerServiceNumID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validGenInfoCustServNumPath));
        driver.findElement(By.id(accActVars.step2HouseNumStreetNameBrgyID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validGenInfoAddressLine1Path));
        driver.findElement(By.id(accActVars.step2AptNumSuiteUnitNumID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validGenInfoAddressLine2Path));
        driver.findElement(By.id(accActVars.step2CityMunicipalityID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validGenInfoCityPath));
        
        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step2RegionSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(jsonVars.validGenInfoStatePath))).click();

        driver.findElement(By.id(accActVars.step2ZipCodeID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validGenInfoZIPCodePath));
        driver.findElement(By.xpath(accActVars.returnStep2PhysicalStoreRadioButtonXpath(
            methods.findJsonStringValue(jsonVars.validGenInfoHasPhysStorePath), false))).click();

        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step2BusinessSizeSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(jsonVars.validGenInfoBusinessSizePath))).click();

        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step2BusinessIndustrySelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(jsonVars.validGenInfoBusinessIndustryPath))).click();

        driver.findElement(By.xpath(accActVars.step2ProductTextXpath))
            .sendKeys(methods.findJsonStringValue(jsonVars.validGenInfoProdDescPath));
        driver.findElement(By.xpath(accActVars.step2BusinessWebsiteXpath))
            .sendKeys(methods.findJsonStringValue(jsonVars.returnValidGenInfoSpecificBusinessLinkPath("1")));
        driver.findElement(By.id(accActVars.step2BusinessHandleID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validGenInfoBusinessHandlePath));
    }

    public void verifySubmitOfBlankThirdStepForm() {

        /* Clear all pre-filled fields. */
        driver.findElement(By.id(accActVars.step3FirstNameID)).clear();
        driver.findElement(By.id(accActVars.step3LastNameID)).clear();

        if (!testBusinessType.equals("Individual")) {
            driver.findElement(By.id(accActVars.step3BusinessLegalNameID)).clear();
        }

        driver.findElement(By.xpath(accActVars.nextBtnXpath)).click();

        methods.testPause(15000);

        // /* Check presence of error messages. */
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_GOVERNMENT_ID)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_FIRST_NAME)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_LAST_NAME)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_DATE_OF_BIRTH)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_ADDRESS_LINE1)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_CITY)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_REGION)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_ZIP_CODE)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnBankFieldErrorMessageXpath(ErrorType.MISSING_BANK)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnBankFieldErrorMessageXpath(ErrorType.MISSING_BANK_ACCOUNT_NAME)).isDisplayed());
        // Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //     accActVars.returnBankFieldErrorMessageXpath(ErrorType.MISSING_BANK_ACCOUNT_NUM)).isDisplayed());

        // /* Error messages specific to the INDIVIDUAL BUSINESS TYPE. */
        // if (testBusinessType.equals("Individual")) {
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_NATIONALITY)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_CITY_OF_BIRTH)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_COUNTRY_OF_BIRTH)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_NATURE_OF_WORK)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SOURCE_OF_FUNDS)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_AGE)).isDisplayed());

        // /* Error messages specific to the SOLE PROPREITORSHIP BUSINESS TYPE. */
        // } else if (testBusinessType.equals("Sole propreitorship")) {
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_DTI_REG_CERT)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_NATIONALITY)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_CITY_OF_BIRTH)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_COUNTRY_OF_BIRTH)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_LEGAL_NAME)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_AGE)).isDisplayed());

        // /* Error messages specific to the PARTNERSHIP TYPE. */
        // } else if (testBusinessType.equals("Partnership")) {
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SEC_CERT_OF_PARTNERSHIP)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SEC_ARTICLES_OF_PARTNERSHIP)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_LEGAL_NAME)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_DATE_OF_INCORPORATION)).isDisplayed());
        
        // /* Error messages specific to the CORPORATION TYPE. */
        // } else {
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SEC_CERT_OF_INCORPORATION)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SEC_BY_LAWS)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SEC_ARTICLES_OF_INCORPORATION)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_LEGAL_NAME)).isDisplayed());
        //     Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
        //         accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_DATE_OF_INCORPORATION)).isDisplayed());
        // }
    }

    public void fillUpValidIndividualBusinessInformationForm() {
        driver.findElement(By.id(accActVars.step3FirstNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoFirstNamePath));
        driver.findElement(By.id(accActVars.step3LastNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoLastNamePath));

        // driver.findElement(By.id(accActVars.step3BirthDateXpath))
        //     .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoBirthDatePath));

        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3NationalitySelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validIndivBizInfoNationalityPath))).click();

        driver.findElement(By.id(accActVars.step3BirthCityID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoBirthCityPath));

        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3BirthCountrySelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validIndivBizInfoBirthCountryPath))).click();
        
        driver.findElement(By.id(accActVars.step3HouseNumStreetNameBrgyID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoAddressLine1Path));
        driver.findElement(By.id(accActVars.step3AptNumSuiteUnitNumID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoAddressLine2Path));
        driver.findElement(By.id(accActVars.step3CityMunicipalityID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoCityPath));
        
        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3RegionSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(jsonVars.validIndivBizInfoStatePath))).click();

        driver.findElement(By.id(accActVars.step3ZipCodeID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoZIPCodePath));
        
        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3NatureOfWorkSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validIndivBizInfoNatureOfWorkPath))).click();
        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3SourceOfFundsSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validIndivBizInfoSourceOfFundsPath))).click();

        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3BusinessAgeSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validIndivBizInfoBusinessAgePath))).click();
        
        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3BankSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validIndivBizInfoBankPath))).click();

        driver.findElement(By.id(accActVars.step3BankAcctNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoBankAcctNamePath));
        driver.findElement(By.id(accActVars.step3BankAcctNumID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoBankAcctNumPath));
    }
    
}

