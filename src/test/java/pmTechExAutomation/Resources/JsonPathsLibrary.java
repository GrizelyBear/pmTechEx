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

    /* JSON Paths relating to the Business Information - SOLE PROPRIETORSHIP form. */
    public String validSolePropBizInfoPath = "$.validSoleProprietorshipBusinessInformationForm";
    public String validSolePropBizInfoFirstNamePath = validSolePropBizInfoPath + ".firstName";
    public String validSolePropBizInfoLastNamePath = validSolePropBizInfoPath + ".lastName";
    public String validSolePropBizInfoBirthDatePath = validSolePropBizInfoPath + ".birthDate";
    public String validSolePropBizInfoNationalityPath = validSolePropBizInfoPath + ".nationality";
    public String validSolePropBizInfoBirthCityPath = validSolePropBizInfoPath + ".cityOfBirth";
    public String validSolePropBizInfoBirthCountryPath = validSolePropBizInfoPath + ".countryOfBirth";
    public String validSolePropBizInfoBusinessNamePath = validSolePropBizInfoPath + ".businessName";
    public String validSolePropBizInfoBusinessAgePath = validSolePropBizInfoPath + ".businessAge";
    public String validSolePropBizInfoBankPath = validSolePropBizInfoPath + ".bank";
    public String validSolePropBizInfoBankAcctNamePath = validSolePropBizInfoPath + ".bankAcctName";
    public String validSolePropBizInfoBankAcctNumPath = validSolePropBizInfoPath + ".bankAcctNum";

    // Business Address
    public String validSolePropBizInfoAddressPath = validSolePropBizInfoPath + ".businessAddress"; 
    public String validSolePropBizInfoAddressLine1Path = validSolePropBizInfoAddressPath + ".addressLine1";
    public String validSolePropBizInfoAddressLine2Path = validSolePropBizInfoAddressPath + ".addressLine2";
    public String validSolePropBizInfoCityPath = validSolePropBizInfoAddressPath + ".addressCity";
    public String validSolePropBizInfoStatePath = validSolePropBizInfoAddressPath + ".addressState";
    public String validSolePropBizInfoZIPCodePath = validSolePropBizInfoAddressPath + ".addressZip";

    /* JSON Paths relating to the Business Information - PARTNERSHIP form. */
    public String validPartnerBizInfoPath = "$.validPartnershipBusinessInformationForm";
    public String validPartnerBizInfoFirstNamePath = validPartnerBizInfoPath + ".firstName";
    public String validPartnerBizInfoLastNamePath = validPartnerBizInfoPath + ".lastName";
    public String validPartnerBizInfoBirthDatePath = validPartnerBizInfoPath + ".birthDate";
    public String validPartnerBizInfoBusinessNamePath = validPartnerBizInfoPath + ".businessName";
    public String validPartnerBizInfoDateOfIncorpPath = validPartnerBizInfoPath + ".dateOfIncorporation";
    public String validPartnerBizInfoBankPath = validPartnerBizInfoPath + ".bank";
    public String validPartnerBizInfoBankAcctNamePath = validPartnerBizInfoPath + ".bankAcctName";
    public String validPartnerBizInfoBankAcctNumPath = validPartnerBizInfoPath + ".bankAcctNum";

    // Business Address
    public String validPartnerBizInfoAddressPath = validPartnerBizInfoPath + ".businessAddress"; 
    public String validPartnerBizInfoAddressLine1Path = validPartnerBizInfoAddressPath + ".addressLine1";
    public String validPartnerBizInfoAddressLine2Path = validPartnerBizInfoAddressPath + ".addressLine2";
    public String validPartnerBizInfoCityPath = validPartnerBizInfoAddressPath + ".addressCity";
    public String validPartnerBizInfoStatePath = validPartnerBizInfoAddressPath + ".addressState";
    public String validPartnerBizInfoZIPCodePath = validPartnerBizInfoAddressPath + ".addressZip";

    /* JSON Paths relating to the Business Information - PARTNERSHIP form. */
    public String validCorpBizInfoPath = "$.validPartnershipBusinessInformationForm";
    public String validCorpBizInfoFirstNamePath = validCorpBizInfoPath + ".firstName";
    public String validCorpBizInfoLastNamePath = validCorpBizInfoPath + ".lastName";
    public String validCorpBizInfoBirthDatePath = validCorpBizInfoPath + ".birthDate";
    public String validCorpBizInfoBusinessNamePath = validCorpBizInfoPath + ".businessName";
    public String validCorpBizInfoDateOfIncorpPath = validCorpBizInfoPath + ".dateOfIncorporation";
    public String validCorpBizInfoBankPath = validCorpBizInfoPath + ".bank";
    public String validCorpBizInfoBankAcctNamePath = validCorpBizInfoPath + ".bankAcctName";
    public String validCorpBizInfoBankAcctNumPath = validCorpBizInfoPath + ".bankAcctNum";

    // Business Address
    public String validCorpBizInfoAddressPath = validCorpBizInfoPath + ".businessAddress"; 
    public String validCorpBizInfoAddressLine1Path = validCorpBizInfoAddressPath + ".addressLine1";
    public String validCorpBizInfoAddressLine2Path = validCorpBizInfoAddressPath + ".addressLine2";
    public String validCorpBizInfoCityPath = validCorpBizInfoAddressPath + ".addressCity";
    public String validCorpBizInfoStatePath = validCorpBizInfoAddressPath + ".addressState";
    public String validCorpBizInfoZIPCodePath = validCorpBizInfoAddressPath + ".addressZip";
}
