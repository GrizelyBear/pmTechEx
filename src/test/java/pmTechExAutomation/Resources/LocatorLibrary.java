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
        
        /* Address fields */
        MISSING_ADDRESS_LINE1 ("The value for address line1 cannot be blank."),
        MISSING_CITY ("The value for address city cannot be blank."),
        MISSING_REGION ("The value for address state cannot be blank."),
        MISSING_ZIP_CODE ("The value for address postal code cannot be blank."),

        /* General Information fields */
        MISSING_BUSINESS_STORE_NAME ("The value for business store name cannot be blank."),
        MISSING_CUSTOMER_SERVICE_NUM ("The value for account customer service number cannot be blank."),
        MISSING_BUSINESS_SIZE ("The value for business size cannot be blank."),
        MISSING_BUSINESS_INDUSTRY ("The value for business industry cannot be blank."),
        MISSING_PRODUCT_DESC ("The value for business description cannot be blank."),
        MISSING_BUSINESS_LINK ("The value for business website cannot be blank."),
        MISSING_BUSINESS_HANDLE ("The value for business handle cannot be blank."),
        DEFAULT_BUSINESS_HANDLE ("The value of the business handle should not be the default value."),

        /* Business Information fields */
        MISSING_GOVERNMENT_ID ("The value for government id cannot be blank."),
        MISSING_DTI_REG_CERT ("The value for dti registration cannot be blank."),
        MISSING_SEC_CERT_OF_PARTNERSHIP ("The value for sec registration cannot be blank."),
        MISSING_SEC_CERT_OF_INCORPORATION ("The value for sec registration cannot be blank."),
        MISSING_SEC_BY_LAWS ("The value for by laws cannot be blank."),
        MISSING_SEC_ARTICLES_OF_PARTNERSHIP ("The value for articles of partnerships cannot be blank."),
        MISSING_SEC_ARTICLES_OF_INCORPORATION ("The value for articles of incorporation cannot be blank."),
        MISSING_FIRST_NAME ("The value for first name cannot be blank."),
        MISSING_LAST_NAME ("The value for last name cannot be blank."),
        MISSING_DATE_OF_BIRTH ("The value for date of birth cannot be blank."),
        MISSING_NATIONALITY ("The nationality passed  is invalid."),
        MISSING_CITY_OF_BIRTH ("City of birth cannot be blank."),
        MISSING_COUNTRY_OF_BIRTH ("Country of birth cannot be blank."),
        MISSING_NATURE_OF_WORK ("The nature of work passed  is invalid."),
        MISSING_SOURCE_OF_FUNDS ("The source of funds passed  is invalid."),
        MISSING_DTI_REG_BUSINESS_NAME ("The value for account dti registered name cannot be blank."),
        MISSING_BUSINESS_LEGAL_NAME ("The value for account legal name cannot be blank."),
        MISSING_BUSINESS_AGE ("This cannot be blank."),
        MISSING_DATE_OF_INCORPORATION ("The value for date of incorporation cannot be blank."),
        MISSING_BANK ("The value for bank id cannot be blank."),
        MISSING_BANK_ACCOUNT_NAME ("The value for bank account name cannot be blank."),
        MISSING_BANK_ACCOUNT_NUM ("The value for bank account number cannot be blank.");

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
        public String step2ZipCodeID = "address.postal_code";
        public String step2PhysicalStoreYesBtnID = "business.brick_and_mortar.true";
        public String step2PhysicalStoreNoBtnID = "business.brick_and_mortar.false";
        public String step2BusinessHandleID = "business.handle";

        public String step2RegionSelectorXpath = "//div[text()='Select region']";
        public String step2BusinessSizeSelectorXpath = "//div[contains(@class, 'employee-count-input')]";
        public String step2BusinessIndustrySelectorXpath = "//div[contains(@class, 'grab-pay-categories-input')]";
        public String step2ProductTextXpath = "//textarea[contains(@class, 'general-information')]";
        public String step2BusinessWebsiteXpath = "//div[@id='business.website']//input";

        /* Step 3 */
        public String step3HeaderXpath = "//h1[text()='Business Information']";

        public String step3GovtIdUploadID = "government_id";
        public String step3DTIRegCertUploadID = "dti_registration";
        public String step3SECRegCertUploadID = "sec_registration";
        public String step3ByLawsUploadID = "by_laws";
        public String step3ArticlesOfPartnershipUploadID = "articles_of_partnerships";
        public String step3ArticlesOfIncorporationUploadID = "articles_of_incorporation";

        public String step3FirstNameID = "first_name";
        public String step3LastNameID = "last_name";
        public String step3BirthCityID = "place_of_birth_city";
        public String step3HouseNumStreetNameBrgyID = "line1";
        public String step3AptNumSuiteUnitNumID = "line2";
        public String step3CityMunicipalityID = "city";
        public String step3ZipCodeID = "postal_code";
        public String step3DTIRegisteredNameID = "dti_registered_name";
        public String step3BusinessLegalNameID = "legal_name";
        public String step3BankAcctNameID = "account_name";
        public String step3BankAcctNumID = "account_number";
        
        public String step3BirthDateXpath = "//input[contains(@class, 'calendar')]";
        public String step3NationalitySelectorXpath = "//div[contains(@class, 'select-nationality')]";
        public String step3BirthCountrySelectorXpath = "//div[contains(@class, 'select-place-of-birth-country')]";
        public String step3RegionSelectorXpath = "//div[text()='Select region']";
        public String step3NatureOfWorkSelectorXpath = "//div[contains(@class, 'select-nature-of-work')]";
        public String step3SourceOfFundsSelectorXpath = "//div[contains(@class, 'select-source-of-funds')]";
        public String step3BusinessAgeSelectorXpath = "//div[contains(@class, 'select business-age')]";
        public String step3DateOfIncorporationXpath = "//span[@id='date_of_incorporation']//input";
        public String step3BankSelectorXpath = "//div[contains(@class, 'select bank')]";

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

        public String returnStep3BusinessTypeRadioButtonXpath(String businessType) {
            return "//h4[text()='" + businessType + "']";
        }

        public String returnDropdownChoiceButtonXpath(String optionName) {
            return "//button[contains(@class, 'select')]/div[text()='" + optionName + "']";
        }

        public String returnFieldErrorMessageXpath(ErrorType expectedError) {
            return "//p[contains(text(), '" + expectedError.returnErrorMsg() + "')]";
        }

        public String returnBankFieldErrorMessageXpath(ErrorType expectedError) {
            return "//span[contains(text(), '" + expectedError.returnErrorMsg() + "')]";
        }

        public String returnBankDropdownChoiceButtonXpath(String bankOptionName) {
            return "//div[text()='" + bankOptionName + "']";
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
