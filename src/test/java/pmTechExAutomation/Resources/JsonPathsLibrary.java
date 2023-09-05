package pmTechExAutomation.Resources;

public class JsonPathsLibrary {
    public String testURLPath = "$.testURL";

    /* JSON Paths relating to the information the user submitted during sign-up. */
    public String signUpInfoPath = "$.signUpInformation";
    public String signUpBusinessNamePath = signUpInfoPath + ".businessName";
    public String signUpFirstNamePath = signUpInfoPath + ".firstName";
    public String signUpLastNamePath = signUpInfoPath + ".lastName";

    /* JSON Paths relating to the General Information form. */
    public String validGenInfoPath = "$.validGeneralInformationForm";
    public String validGenInfoBusinessNamePath = validGenInfoPath + ".businessStoreName";
    public String validGenInfoCustServNumPath = validGenInfoPath + ".customerServiceNum";
    public String validGenInfoHasPhysStorePath = validGenInfoPath + ".hasPhysicalStore";
    public String validGenInfoBusinessSizePath = validGenInfoPath + ".businessSize";
    public String validGenInfoBusinessIndustryPath = validGenInfoPath + ".businessIndustry";
    public String validGenInfoProdDescPath = validGenInfoPath + ".productDesc";
    public String validGenInfoBusinessLinksPath = validGenInfoPath + ".businessLinks";
    public String validGenInfoBusinessHandlePath = validGenInfoPath + ".businessHandle";

    // Business Address
    public String validGenInfoBusinessAddressPath = validGenInfoPath + ".businessAddress"; 
    public String validGenInfoAddressLine1Path = validGenInfoBusinessAddressPath + ".addressLine1";
    public String validGenInfoAddressLine2Path = validGenInfoBusinessAddressPath + ".addressLine2";
    public String validGenInfoCityPath = validGenInfoBusinessAddressPath + ".addressCity";
    public String validGenInfoStatePath = validGenInfoBusinessAddressPath + ".addressState";
    public String validGenInfoZIPCodePath = validGenInfoBusinessAddressPath + ".addressZip";

    public String returnValidGenInfoSpecificBusinessLinkPath(String linkNum) {
        return validGenInfoBusinessLinksPath + ".businessLink" + linkNum;
    }

    /* JSON Paths relating to the Business Information - INDIVIDUAL form. */
    public String validIndivBizInfoPath = "$.validIndividualBusinessInformationForm";
    public String validIndivBizInfoFirstNamePath = validIndivBizInfoPath + ".firstName";
    public String validIndivBizInfoLastNamePath = validIndivBizInfoPath + ".lastName";
    public String validIndivBizInfoBirthDatePath = validIndivBizInfoPath + ".birthDate";
    public String validIndivBizInfoNationalityPath = validIndivBizInfoPath + ".nationality";
    public String validIndivBizInfoBirthCityPath = validIndivBizInfoPath + ".cityOfBirth";
    public String validIndivBizInfoBirthCountryPath = validIndivBizInfoPath + ".countryOfBirth";
    public String validIndivBizInfoNatureOfWorkPath = validIndivBizInfoPath + ".natureOfWork";
    public String validIndivBizInfoSourceOfFundsPath = validIndivBizInfoPath + ".sourceOfFunds";
    public String validIndivBizInfoBusinessAgePath = validIndivBizInfoPath + ".businessAge";
    public String validIndivBizInfoBankPath = validIndivBizInfoPath + ".bank";
    public String validIndivBizInfoBankAcctNamePath = validIndivBizInfoPath + ".bankAcctName";
    public String validIndivBizInfoBankAcctNumPath = validIndivBizInfoPath + ".bankAcctNum";

    // Business Address
    public String validIndivBizInfoAddressPath = validIndivBizInfoPath + ".businessAddress"; 
    public String validIndivBizInfoAddressLine1Path = validIndivBizInfoAddressPath + ".addressLine1";
    public String validIndivBizInfoAddressLine2Path = validIndivBizInfoAddressPath + ".addressLine2";
    public String validIndivBizInfoCityPath = validIndivBizInfoAddressPath + ".addressCity";
    public String validIndivBizInfoStatePath = validIndivBizInfoAddressPath + ".addressState";
    public String validIndivBizInfoZIPCodePath = validIndivBizInfoAddressPath + ".addressZip";
}
