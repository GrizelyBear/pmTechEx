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
    public String validGenInfoBusinessAddressPath = validGenInfoBusinessNamePath + ".businessAddress"; 
    public String validGenInfoAddressLine1Path = validGenInfoBusinessAddressPath + ".addressLine1";
    public String validGenInfoAddressLine2Path = validGenInfoBusinessAddressPath + ".addressLine2";
    public String validGenInfoCityPath = validGenInfoBusinessAddressPath + ".addressCity";
    public String validGenInfoStatePath = validGenInfoBusinessAddressPath + ".addressState";
    public String validGenInfoZIPCodePath = validGenInfoBusinessAddressPath + ".addressZip";

    public String returnValidGenInfoSpecificBusinessLinkPath(String linkNum) {
        return validGenInfoBusinessLinksPath + ".businessLink" + linkNum;
    }
}
