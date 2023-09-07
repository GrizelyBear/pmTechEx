package pmTechExAutomation.TestCases;

import java.io.BufferedReader;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
    public long pageTimeout = 30;

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
    public void verifyUserLogin() {
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

    @Test (dependsOnMethods = {"verifyUserLogin"})
    public void verifyAccountActivationFirstStep() {
        
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
        methods.waitUntilElementClickable(driver, wait, "xpath", accActVars.nextBtnXpath).click();
    }

    @Test (dependsOnMethods = {"verifyAccountActivationFirstStep"})
    public void verifyAccountActivationSecondStep() {
                
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

        /* Assert the initial value of the Business Store Name. */
        Assert.assertEquals(
            driver.findElement(By.id(accActVars.step2BusinessStoreNameID)).getAttribute("value"),
            methods.findJsonStringValue(jsonVars.signUpBusinessNamePath));

        /* Assert that the Business Handle has a default value. */
        defaultBusinessHandle = driver.findElement(By.id(accActVars.step2BusinessHandleID)).getAttribute("value");

        Assert.assertTrue(!defaultBusinessHandle.isEmpty());
    }

    @Test (dependsOnMethods = {"verifyAccountActivationSecondStep"})
    public void verifyAccountActivationSecondStepErrorHandling() {
        verifySubmitOfBlankSecondStepForm();

        /* Assert that the Roadmap Status of this step is "With issues." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.SECOND)).getText(),
            StepStatus.WITH_ISSUES.returnStepStatusLabel());
        
        verifySubmitOfDefaultBusinessHandleSecondStepForm();

        /* Assert that the Roadmap Status of this step is "With issues." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.SECOND)).getText(),
            StepStatus.WITH_ISSUES.returnStepStatusLabel());
    }

    @Test (dependsOnMethods = {"verifyAccountActivationSecondStepErrorHandling"})
    public void verifyAccountActivationSecondStepSuccessfulSubmit() {
        fillUpValidGeneralInformationForm();

        methods.waitUntilElementClickable(driver, wait, "xpath", accActVars.nextBtnXpath).click();
    }

    @Test (dependsOnMethods = {"verifyAccountActivationSecondStepSuccessfulSubmit"})
    public void verifyAccountActivationThirdStepChooseBusinessType() {

        /* Assert that the user has been properly redirected to Step 3 of the Account Activation module. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3HeaderXpath).isDisplayed());

        /* Assert that the Roadmap Status of this step is "In progress." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.THIRD)).getText(),
            StepStatus.IN_PROGRESS.returnStepStatusLabel());

        /* Assert that the Roadmap Status of the previous steps are "Completed." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.FIRST)).getText(),
            StepStatus.COMPLETED.returnStepStatusLabel());
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.SECOND)).getText(),
            StepStatus.COMPLETED.returnStepStatusLabel());

        methods.waitUntilElementClickable(driver, wait, "xpath",
            accActVars.returnStep3BusinessTypeRadioButtonXpath(testBusinessType)).click();
        methods.waitUntilElementClickable(driver, wait, "xpath", accActVars.nextBtnXpath).click();
    }

    @Test (dependsOnMethods = {"verifyAccountActivationThirdStepChooseBusinessType"})
    public void verifyAccountActivationThirdStepBusinessFormInitialValues() {

        /* Assert the initial values of the Legal Name. */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "id",
            accActVars.step3FirstNameID).getAttribute("value"),
            methods.findJsonStringValue(jsonVars.signUpFirstNamePath));
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "id",
            accActVars.step3LastNameID).getAttribute("value"),
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
        } else if (testBusinessType.equals("Sole proprietorship")) {
            fillUpValidSoleProprietorshipBusinessInformationForm();
        } else if (testBusinessType.equals("Partnership")) {
            fillUpValidPartnershipBusinessInformationForm();
        } else {
            fillUpValidCorporationBusinessInformationForm();
        }

        methods.waitUntilElementClickable(driver, wait, "xpath", accActVars.nextBtnXpath).click();
    }

    @Test (dependsOnMethods = {"verifyAccountActivationThirdStepSuccessfulSubmit"})
    public void verifyFourthStepStatementOfAcceptanceLinks() {

        /* Assert that the user has been properly redirected to Step 3 of the Account Activation module. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step4HeaderXpath).isDisplayed());

        /* Assert that the Roadmap Status of this step is "In progress." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.FOURTH)).getText(),
            StepStatus.IN_PROGRESS.returnStepStatusLabel());

        /* Assert that the Roadmap Status of the previous steps are "Completed." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.FIRST)).getText(),
            StepStatus.COMPLETED.returnStepStatusLabel());
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.SECOND)).getText(),
            StepStatus.COMPLETED.returnStepStatusLabel());
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.THIRD)).getText(),
            StepStatus.COMPLETED.returnStepStatusLabel());

        verifyTermsOfUseLink();
        verifyPrivacyPolicyLink();
        verifyPayMongoLink();
    }

    @Test (dependsOnMethods = {"verifyFourthStepStatementOfAcceptanceLinks"})
    public void verifyFourthStepStatementOfAcceptanceErrorHandling() {
        verifySubmitOfBlankFourthStepForm();

        /* Assert that the Roadmap Status of this step is "With issues." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.FOURTH)).getText(),
            StepStatus.WITH_ISSUES.returnStepStatusLabel());
    }

    @Test (dependsOnMethods = {"verifyFourthStepStatementOfAcceptanceErrorHandling"})
    public void verifyFourthStepSubmitStatementOfAcceptance() {
        methods.waitUntilElementClickable(driver, wait, "xpath",
            accActVars.step4SignatureCanvasXpath).click();
        methods.waitUntilElementClickable(driver, wait, "xpath", accActVars.nextBtnXpath).click();

        /* Assert that the user is now on the Account Activation Finish page */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.goToDashboardBtnXpath).isDisplayed());

        /* Assert that the Roadmap Status of all steps are "Completed." */
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.FIRST)).getText(),
            StepStatus.COMPLETED.returnStepStatusLabel());
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.SECOND)).getText(),
            StepStatus.COMPLETED.returnStepStatusLabel());
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.THIRD)).getText(),
            StepStatus.COMPLETED.returnStepStatusLabel());
        Assert.assertEquals(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnStepRoadmapStatusXpath(Step.FOURTH)).getText(),
            StepStatus.COMPLETED.returnStepStatusLabel());
    }

    @AfterClass
    public void tearDown(){
        try {
            driver.close();
            driver.quit();
            fileReader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /************************************************************************/
    /************ Methods below are specific for this test only. ************/
    /************************************************************************/
    
    public void verifyFirstStepDocumentsLink() {
        String step1Tab = driver.getWindowHandle();

        /* Verify if the Documents link opens the correct tab. */
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step1DocumentsLinkXpath).click();
        methods.switchToNewTab(driver, step1Tab);

        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            otherVars.requiredDocumentsPageHeaderXpath).isDisplayed());

        driver.close(); // Close new tab
        driver.switchTo().window(step1Tab); // Go back to original tab
    }

    public void verifyFirstStepRestrictedBusinessesLink() {
        String step1Tab = driver.getWindowHandle();

        /* Verify if the Restricted Businesses link opens the correct tab. */
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step1RestrictedBusinessesLinkXpath).click();
        methods.switchToNewTab(driver, step1Tab);

        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            otherVars.restrictedBusinessesPageHeaderXpath).isDisplayed());

        driver.close(); // Close new tab
        driver.switchTo().window(step1Tab); // Go back to original tab
    }

    public void verifySubmitOfBlankSecondStepForm() {

        /* Clear all pre-filled fields. */
        methods.waitUntilElementVisible(driver, wait, "id", accActVars.step2BusinessStoreNameID).clear();
        methods.waitUntilElementVisible(driver, wait, "id", accActVars.step2BusinessHandleID).clear();

        methods.waitUntilElementClickable(driver, wait, "xpath", accActVars.nextBtnXpath).click();

        /* Check presence of error messages. */
        checkGeneralInformationAssertionsOfErrorMsgs();
    }

    public void verifySubmitOfDefaultBusinessHandleSecondStepForm() {
        
        /* Fill the Business Handle field with the default Business Handle */
        methods.waitUntilElementVisible(driver, wait, "id", accActVars.step2BusinessHandleID)
            .sendKeys(defaultBusinessHandle);

        methods.waitUntilElementClickable(driver, wait, "xpath", accActVars.nextBtnXpath).click();

        /* Check presence of error messages. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.DEFAULT_BUSINESS_HANDLE)).isDisplayed());

        /* Revert back to blank state */
        methods.waitUntilElementVisible(driver, wait, "id", accActVars.step2BusinessHandleID).clear();
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
            .sendKeys(methods.findJsonStringValue(jsonVars.validGenInfoBusinessHandlePath)
                + String.valueOf(ThreadLocalRandom.current().nextInt()));
    }

    public void verifySubmitOfBlankThirdStepForm() {

        /* Clear all pre-filled fields. */
        methods.waitUntilElementVisible(driver, wait, "id", accActVars.step3FirstNameID).clear();
        methods.waitUntilElementVisible(driver, wait, "id", accActVars.step3LastNameID).clear();

        if (!testBusinessType.equals("Individual")) {
            if (testBusinessType.equals("Sole proprietorship")) {
                methods.waitUntilElementVisible(driver, wait, "id",
                    accActVars.step3DTIRegisteredNameID).clear();
            } else {
                methods.waitUntilElementVisible(driver, wait, "id",
                    accActVars.step3BusinessLegalNameID).clear();
            }
        }

        methods.waitUntilElementClickable(driver, wait, "xpath", accActVars.nextBtnXpath).click();

        /* Check presence of error messages. */
        checkBusinessInformationAssertionsOfErrorMsgs();
    }

    /* Method to fill up Step 3 - Partnership Business Information Form with valid values. */
    public void fillUpValidIndividualBusinessInformationForm() {
        driver.findElement(By.id(accActVars.step3GovtIdUploadID))
            .sendKeys(methods.returnFileLocation("sampleImage_1.png", ""));
        
        /* Assert that the files have been uploaded properly. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnUploadedFileXpath("sampleImage_1.png")).isDisplayed());
        
        driver.findElement(By.id(accActVars.step3FirstNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoFirstNamePath));
        driver.findElement(By.id(accActVars.step3LastNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoLastNamePath));

        driver.findElement(By.xpath(accActVars.step3BirthDateXpath)).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step3DateInputXpath)
            .sendKeys(methods.findJsonStringValue(jsonVars.validIndivBizInfoBirthDatePath), Keys.RETURN);

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
            accActVars.step3IndivBusinessAgeSelectorXpath).click();
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

    /* Method to fill up Step 3 - Partnership Business Information Form with valid values. */
    public void fillUpValidSoleProprietorshipBusinessInformationForm() {
        driver.findElement(By.id(accActVars.step3GovtIdUploadID))
            .sendKeys(methods.returnFileLocation("sampleImage_1.png", ""));
        driver.findElement(By.id(accActVars.step3DTIRegCertUploadID))
            .sendKeys(methods.returnFileLocation("sampleImage_2.jpeg", ""));

        /* Assert that the files have been uploaded properly. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnUploadedFileXpath("sampleImage_1.png")).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnUploadedFileXpath("sampleImage_2.jpeg")).isDisplayed());

        driver.findElement(By.id(accActVars.step3FirstNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validSolePropBizInfoFirstNamePath));
        driver.findElement(By.id(accActVars.step3LastNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validSolePropBizInfoLastNamePath));

        driver.findElement(By.xpath(accActVars.step3BirthDateXpath)).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step3DateInputXpath)
            .sendKeys(methods.findJsonStringValue(jsonVars.validSolePropBizInfoBirthDatePath), Keys.RETURN);

        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3NationalitySelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validSolePropBizInfoNationalityPath))).click();

        driver.findElement(By.id(accActVars.step3BirthCityID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validSolePropBizInfoBirthCityPath));

        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3BirthCountrySelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validSolePropBizInfoBirthCountryPath))).click();
        
        driver.findElement(By.id(accActVars.step3HouseNumStreetNameBrgyID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validSolePropBizInfoAddressLine1Path));
        driver.findElement(By.id(accActVars.step3AptNumSuiteUnitNumID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validSolePropBizInfoAddressLine2Path));
        driver.findElement(By.id(accActVars.step3CityMunicipalityID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validSolePropBizInfoCityPath));
        
        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3RegionSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validSolePropBizInfoStatePath))).click();

        driver.findElement(By.id(accActVars.step3ZipCodeID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validSolePropBizInfoZIPCodePath));
        driver.findElement(By.id(accActVars.step3DTIRegisteredNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validSolePropBizInfoBusinessNamePath));

        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3SolePropBusinessAgeSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validSolePropBizInfoBusinessAgePath))).click();
        
        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3BankSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validSolePropBizInfoBankPath))).click();

        driver.findElement(By.id(accActVars.step3BankAcctNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validSolePropBizInfoBankAcctNamePath));
        driver.findElement(By.id(accActVars.step3BankAcctNumID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validSolePropBizInfoBankAcctNumPath));
    }

    /* Method to fill up Step 3 - Partnership Business Information Form with valid values. */
    public void fillUpValidPartnershipBusinessInformationForm() {
        driver.findElement(By.id(accActVars.step3GovtIdUploadID))
            .sendKeys(methods.returnFileLocation("sampleImage_1.png", ""));
        driver.findElement(By.id(accActVars.step3SECRegCertUploadID))
            .sendKeys(methods.returnFileLocation("sampleImage_2.jpeg", ""));
        driver.findElement(By.id(accActVars.step3ArticlesOfPartnershipUploadID))
            .sendKeys(methods.returnFileLocation("sampleImage_3.png", ""));

        /* Assert that the files have been uploaded properly. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnUploadedFileXpath("sampleImage_1.png")).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnUploadedFileXpath("sampleImage_2.jpeg")).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnUploadedFileXpath("sampleImage_3.png")).isDisplayed());

        driver.findElement(By.id(accActVars.step3FirstNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validPartnerBizInfoFirstNamePath));
        driver.findElement(By.id(accActVars.step3LastNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validPartnerBizInfoLastNamePath));

        driver.findElement(By.xpath(accActVars.step3BirthDateXpath)).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step3DateInputXpath)
            .sendKeys(methods.findJsonStringValue(jsonVars.validPartnerBizInfoBirthDatePath), Keys.RETURN);
        
        driver.findElement(By.id(accActVars.step3HouseNumStreetNameBrgyID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validPartnerBizInfoAddressLine1Path));
        driver.findElement(By.id(accActVars.step3AptNumSuiteUnitNumID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validPartnerBizInfoAddressLine2Path));
        driver.findElement(By.id(accActVars.step3CityMunicipalityID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validPartnerBizInfoCityPath));
        
        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3RegionSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validPartnerBizInfoStatePath))).click();

        driver.findElement(By.id(accActVars.step3ZipCodeID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validPartnerBizInfoZIPCodePath));
        driver.findElement(By.id(accActVars.step3BusinessLegalNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validPartnerBizInfoBusinessNamePath));

        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3DateOfIncorporationXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step3DateInputXpath)
            .sendKeys(methods.findJsonStringValue(jsonVars.validPartnerBizInfoDateOfIncorpPath), Keys.RETURN);
        
        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3BankSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validPartnerBizInfoBankPath))).click();

        driver.findElement(By.id(accActVars.step3BankAcctNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validPartnerBizInfoBankAcctNamePath));
        driver.findElement(By.id(accActVars.step3BankAcctNumID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validPartnerBizInfoBankAcctNumPath));
    }

    /* Method to fill up Step 3 - Corporation Business Information Form with valid values. */
    public void fillUpValidCorporationBusinessInformationForm() {
        driver.findElement(By.id(accActVars.step3GovtIdUploadID))
            .sendKeys(methods.returnFileLocation("sampleImage_1.png", ""));
        driver.findElement(By.id(accActVars.step3SECRegCertUploadID))
            .sendKeys(methods.returnFileLocation("sampleImage_2.jpeg", ""));
        driver.findElement(By.id(accActVars.step3ByLawsUploadID))
            .sendKeys(methods.returnFileLocation("sampleImage_3.png", ""));
        driver.findElement(By.id(accActVars.step3ArticlesOfIncorporationUploadID))
            .sendKeys(methods.returnFileLocation("sampleImage_4.png", ""));

        /* Assert that the files have been uploaded properly. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnUploadedFileXpath("sampleImage_1.png")).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnUploadedFileXpath("sampleImage_2.jpeg")).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnUploadedFileXpath("sampleImage_3.png")).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnUploadedFileXpath("sampleImage_4.png")).isDisplayed());

        driver.findElement(By.id(accActVars.step3FirstNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validCorpBizInfoFirstNamePath));
        driver.findElement(By.id(accActVars.step3LastNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validCorpBizInfoLastNamePath));

        driver.findElement(By.xpath(accActVars.step3BirthDateXpath)).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step3DateInputXpath)
            .sendKeys(methods.findJsonStringValue(jsonVars.validCorpBizInfoBirthDatePath), Keys.RETURN);
        
        driver.findElement(By.id(accActVars.step3HouseNumStreetNameBrgyID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validCorpBizInfoAddressLine1Path));
        driver.findElement(By.id(accActVars.step3AptNumSuiteUnitNumID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validCorpBizInfoAddressLine2Path));
        driver.findElement(By.id(accActVars.step3CityMunicipalityID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validCorpBizInfoCityPath));
        
        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3RegionSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validCorpBizInfoStatePath))).click();

        driver.findElement(By.id(accActVars.step3ZipCodeID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validCorpBizInfoZIPCodePath));
        driver.findElement(By.id(accActVars.step3BusinessLegalNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validCorpBizInfoBusinessNamePath));

        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3DateOfIncorporationXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step3DateInputXpath)
            .sendKeys(methods.findJsonStringValue(jsonVars.validCorpBizInfoDateOfIncorpPath), Keys.RETURN);
        
        methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.step3BankSelectorXpath).click();
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars
            .returnDropdownChoiceButtonXpath(methods.findJsonStringValue(
                jsonVars.validCorpBizInfoBankPath))).click();

        driver.findElement(By.id(accActVars.step3BankAcctNameID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validCorpBizInfoBankAcctNamePath));
        driver.findElement(By.id(accActVars.step3BankAcctNumID))
            .sendKeys(methods.findJsonStringValue(jsonVars.validCorpBizInfoBankAcctNumPath));
    }

    /* Checks error messages on Step 2 - General Information */
    public void checkGeneralInformationAssertionsOfErrorMsgs() {
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

    /* Checks error messages on Step 3 - General Information */
    public void checkBusinessInformationAssertionsOfErrorMsgs() {
            
        /* Error messages for all business types. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_GOVERNMENT_ID)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_FIRST_NAME)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_LAST_NAME)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_DATE_OF_BIRTH)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_ADDRESS_LINE1)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_CITY)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_REGION)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_ZIP_CODE)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnSpanFieldErrorMessageXpath(ErrorType.MISSING_BANK)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnSpanFieldErrorMessageXpath(ErrorType.MISSING_BANK_ACCOUNT_NAME)).isDisplayed());
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnSpanFieldErrorMessageXpath(ErrorType.MISSING_BANK_ACCOUNT_NUM)).isDisplayed());

        /* Error messages specific to the INDIVIDUAL BUSINESS TYPE. */
        if (testBusinessType.equals("Individual")) {
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_NATIONALITY)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_CITY_OF_BIRTH)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_COUNTRY_OF_BIRTH)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_NATURE_OF_WORK)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SOURCE_OF_FUNDS)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_AGE)).isDisplayed());

        /* Error messages specific to the SOLE PROPRIETORSHIP BUSINESS TYPE. */
        } else if (testBusinessType.equals("Sole proprietorship")) {
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_DTI_REG_CERT)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_NATIONALITY)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_CITY_OF_BIRTH)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_COUNTRY_OF_BIRTH)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_DTI_REG_BUSINESS_NAME)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_AGE)).isDisplayed());

        /* Error messages specific to the PARTNERSHIP TYPE. */
        } else if (testBusinessType.equals("Partnership")) {
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SEC_CERT_OF_PARTNERSHIP)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SEC_ARTICLES_OF_PARTNERSHIP)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_LEGAL_NAME)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_DATE_OF_INCORPORATION)).isDisplayed());
        
        /* Error messages specific to the CORPORATION TYPE. */
        } else {
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SEC_CERT_OF_INCORPORATION)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SEC_BY_LAWS)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SEC_ARTICLES_OF_INCORPORATION)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnSpanFieldErrorMessageXpath(ErrorType.MISSING_BUSINESS_LEGAL_NAME)).isDisplayed());
            Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
                accActVars.returnSpanFieldErrorMessageXpath(ErrorType.MISSING_DATE_OF_INCORPORATION)).isDisplayed());
        }
    }
 
    public void verifyTermsOfUseLink() {
        String step4Tab = driver.getWindowHandle();

        /* Verify if the Documents link opens the correct tab. */
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step4TermsOfUseLinkXpath).click();
        methods.switchToNewTab(driver, step4Tab);

        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            otherVars.termsOfUseHeaderXpath).isDisplayed());

        driver.close(); // Close new tab
        driver.switchTo().window(step4Tab); // Go back to original tab
    }

    public void verifyPrivacyPolicyLink() {
        String step4Tab = driver.getWindowHandle();

        /* Verify if the Documents link opens the correct tab. */
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step4PrivacyPolicyLinkXpath).click();
        methods.switchToNewTab(driver, step4Tab);

        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            otherVars.privacyPolicyHeaderXpath).isDisplayed());

        driver.close(); // Close new tab
        driver.switchTo().window(step4Tab); // Go back to original tab
    }

    public void verifyPayMongoLink() {
        String step4Tab = driver.getWindowHandle();

        /* Verify if the Documents link opens the correct tab. */
        methods.waitUntilElementVisible(driver, wait, "xpath", accActVars.step4PayMongoWebsiteLinkXpath).click();
        methods.switchToNewTab(driver, step4Tab);

        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            otherVars.payMongoWebsiteHeaderXpath).isDisplayed());

        driver.close(); // Close new tab
        driver.switchTo().window(step4Tab); // Go back to original tab
    }

    public void verifySubmitOfBlankFourthStepForm() {
        methods.waitUntilElementClickable(driver, wait, "xpath", accActVars.nextBtnXpath).click();

        /* Check presence of error messages. */
        Assert.assertTrue(methods.waitUntilElementVisible(driver, wait, "xpath",
            accActVars.returnFieldErrorMessageXpath(ErrorType.MISSING_SIGNATURE)).isDisplayed());
    }
}

