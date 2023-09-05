package pmTechExAutomation.Resources;

public class LocatorLibrary {

    /* Custom data type to signify the different steps under the Account Activation module */
    public enum Step {
        FIRST ("What is KYC?"),
        SECOND ("General Information"),
        THIRD ("Business Information"),
        FOURTH ("Statement of Acceptance");

        private final String stepLabel;

        Step(String stepLabel) {
            this.stepLabel = stepLabel;
        }

        String returnStepLabel() {
            return this.stepLabel;
        }
    }

    /* Custom data type to signify the different status for the steps under the Account Activation module */
    public enum StepStatus {
        IN_PROGRESS ("In progress"),
        COMPLETED ("Completed"),
        WITH_ISSUES ("With issues");

        private final String stepStatusLabel;

        StepStatus(String stepStatusLabel) {
            this.stepStatusLabel = stepStatusLabel;
        }

        public String returnStepStatusLabel() {
            return this.stepStatusLabel;
        }
    }

    /* Custom data type to signify the different error messages which can be seen in the Account Activation module */
    public enum ErrorType {
        MISSING_BUSINESS_STORE_NAME ("The value for business store name cannot be blank."),
        MISSING_CUSTOMER_SERVICE_NUM ("The value for account customer service number cannot be blank."),
        MISSING_ADDRESS_LINE1 ("The value for address line1 cannot be blank."),
        MISSING_CITY ("The value for address city cannot be blank."),
        MISSING_REGION ("The value for address state cannot be blank."),
        MISSING_ZIP_CODE ("The value for address postal code cannot be blank."),
        MISSING_BUSINESS_SIZE ("The value for business size cannot be blank."),
        MISSING_BUSINESS_INDUSTRY ("The value for business industry cannot be blank."),
        MISSING_PRODUCT_DESC ("The value for business description cannot be blank."),
        MISSING_BUSINESS_LINK ("The value for business website cannot be blank."),
        MISSING_BUSINESS_HANDLE ("The value for business handle cannot be blank."),
        DEFAULT_BUSINESS_HANDLE ("The value of the business handle should not be the default value.");

        private final String errorMsg;

        ErrorType(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String returnErrorMsg() {
            return this.errorMsg;
        }
    }
    
    /* Purpose: Contains XPaths and IDs relevant to the Log-in Page */
    public class LogInPageVariables {
        public String logInHeaderXpath = "//span[text()[normalize-space()='Log in to PayMongo']]";
        
        public String emailAddressInputXpath = "//input[@name='email']";
        public String passwordInputXpath = "//input[@name='password']";
        public String submitBtnXpath = "//button[@type='submit']";
    }

    /* Purpose: Contains XPaths and IDs relevant to the Account Activation Pages */
    public class AccountActivationVariables {
        public String radioBtnUncheckedContainerXpath = "//span[contains(@class, 'radio-button-container')]";
        public String radioBtnCheckedContainerXpath = "//span[contains(@class, 'radio-button-container checked')]";

        public String nextBtnXpath = "//button[contains(@class, 'step__next-button')]";

        /* Step 1 */
        public String step1KYCFormXpath = "//div[@class='kyc-form']";
        public String step1HeaderXpath = step1KYCFormXpath + "//div[text()='Welcome to PayMongo!']";
        public String step1DocumentsLinkXpath = step1KYCFormXpath + "//a[text()='documents']";
        public String step1RestrictedBusinessesLinkXpath = step1KYCFormXpath + "//a[text()='Restricted Businesses.']";

        /* Step 2 */
        public String step2HeaderXpath = "//h1[text()='General Information']";

        public String step2BusinessStoreNameID = "account.trade_name";
        public String step2CustomerServiceNumID = "account.customer_service_number";
        public String step2HouseNumStreetNameBrgyID = "address.line1";
        public String step2AptNumSuiteUnitNumID = "address.line2";
        public String step2CityMunicipalityID = "address.city";
        public String step2RegionSelectorID = "address.state";
        public String step2ZipCodeID = "address.postal_code";
        public String step2PhysicalStoreYesBtnID = "business.brick_and_mortar.true";
        public String step2PhysicalStoreNoBtnID = "business.brick_and_mortar.false";
        public String step2BusinessSizeSelectorID = "account.business_size";
        public String step2BusinessIndustrySelectorID = "business.grab_pay_category";
        public String step2BusinessHandleID = "business.handle";

        public String step2ProductTextXpath = "//textarea[contains(@class, 'general-information')]";
        public String step2BusinessWebsiteXpath = "//div[@id='business.website']//input";

        /* Functions */
        public String returnStepRoadmapStatusXpath(Step chosenStep) {
            return "//span[text()[normalize-space()='" + chosenStep.returnStepLabel()
                + "']]/preceding-sibling::span";
        }

        public String returnStep2PhysicalStoreRadioButtonXpath(String radioButtonValue, boolean isChosen) {
            if (isChosen) {
                if (radioButtonValue.equals("Yes")){
                    return radioBtnCheckedContainerXpath + "/input[@id='" + step2PhysicalStoreYesBtnID + "']";
                } else {
                    return radioBtnCheckedContainerXpath + "/input[@id='" + step2PhysicalStoreNoBtnID + "']";
                }
            } else {
                if (radioButtonValue.equals("Yes")){
                    return radioBtnUncheckedContainerXpath + "/input[@id='" + step2PhysicalStoreYesBtnID + "']";
                } else {
                    return radioBtnUncheckedContainerXpath + "/input[@id='" + step2PhysicalStoreNoBtnID + "']";
                }
            }
        }

        public String returnFieldErrorMessageXpath(ErrorType expectedError) {
            return "//p[contains(text(), '" + expectedError.returnErrorMsg() + "')]";
        }
    }

    /* Purpose: Contains XPaths and IDs that are not part of the Log-In and Account Activation modules */
    public class OtherPageVariables {
        public String requiredDocumentsPageHeaderXpath =
            "//header[text()='What are the documents required to activate my account?']";
        public String restrictedBusinessesPageHeaderXpath =
            "//h2[text()='Restricted Businesses']";
    }
}
