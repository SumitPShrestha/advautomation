package com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.inputelements.AutoSuggest;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CaseCorrespondenceSection extends AbstractWebComponent {

    @FindBy(xpath = "//*[@id=\"preview\"]")
    public  WebElement previewBtn;

    @FindBy(xpath = "//*[@id=\"saveModal\"]")
    public  WebElement approveBtn;

    @FindBy(xpath = "//*[@id=\"closeModal\"]")
    public  WebElement rejectBtn;

    //Correspondence Specifics
    @CustomElement(xpath =  "//*[@id=\"EventCaseMasterKey\"]")
    public DropDown caseNumber;

    @CustomElement(xpath =  "//*[@id=\"CommunicationDirection\"]")
    public DropDown direction;

    @CustomElement(xpath =  "//*[@id=\"CommunicationType\"]")
    public DropDown communicationMethod;

    @CustomElement(xpath =  "//*[@id=\"RelatedCaseCommunicationKey\"]")
    public DropDown relatedToPriorCommunication;

    @CustomElement(xpath =  "//*[@id=\"LetterTemplateKey\"]")
    public DropDown correspondence;

    @CustomElement(xpath =  "//*[@id=\"CommunicationSubject\"]")
    public TextBox subject;

    @CustomElement(xpath = "//*[@id=\"CommunicationNote\"]")
    public TextBox notes;

    @FindBy(xpath = "//*[@id=\"multipleupload\"]")
    public WebElement chooseFilesBtn;

    @FindBy(xpath = "//*[@id=\"upload\"]")
    public WebElement uploadBtn;

    //need to implement checkbox
    /*@CustomElement(xpath =  "//*[@id=\"IncludeMPR\"]")
    public CheckBox includeCSB;*/

    //addressee section
    @CustomElement(xpath =  "//*[@id=\"PartyRole\"]")
    public DropDown partyRole;

    @CustomElement(xpath =  "//*[@id=\"EventCasePartyKey\"]")
    public DropDown partyName;

    @CustomElement(xpath =  "//*[@id=\"EventCasePartyContactKey\"]")
    public DropDown contactName;

    @CustomElement(xpath =  "//*[@id=\"ContactTitle\"]")
    public TextBox title;

    @CustomElement(xpath =  "//*[@id=\"ContactAddress1\"]")
    public TextBox addressLine1;

    @CustomElement(xpath =  "//*[@id=\"ContactAddress2\"]")
    public TextBox addressLine2;

    @CustomElement(xpath =  "//*[@id=\"ContactCity\"]")
    public AutoSuggest city;

    @CustomElement(xpath =  "//*[@id=\"ContactState\"]")
    public DropDown state;

    @CustomElement(xpath =  "//*[@id=\"ContactZip\"]")
    public TextBox zip;

    @CustomElement(xpath =  "//*[@id=\"ContactEmail\"]")
    public TextBox email;

    @CustomElement(xpath =  "//*[@id=\"ContactPhone1\"]")
    public TextBox businessPhone;

    @CustomElement(xpath =  "//*[@id=\"ContactPhone1Ext\"]")
    public TextBox businessPhoneExt;

    @CustomElement(xpath =  "//*[@id=\"ContactPhone2\"]")
    public TextBox cellPhone;

    @CustomElement(xpath =  "//*[@id=\"ContactFax\"]")
    public TextBox fax;


    //Implementation on post page after button clicks
    /*@LogStep(step = "Clicking On Choose Files Button")
    public a clickOnChooseFilesBtn(){
        chooseFilesBtn.click();
        return new a(getDriver());
    }

    @LogStep(step = "Clicking On Upload Button")
    public a clickOnUploadBtn(){
        uploadBtn.click();
        return new a(getDriver());
    }
    */

    @LogStep(step = "Clicking On Preview Button")
    public CorrespondencePreviewModal clickOnPreviewBtn(){
        previewBtn.click();
        return new CorrespondencePreviewModal(getDriver());
    }

    @LogStep(step = "Clicking On Approve Button")
    public CaseCorrespondenceTab clickOnApproveBtn(){
        approveBtn.click();
        return new CaseCorrespondenceTab(getDriver());
    }

    @LogStep(step = "Clicking On Reject Button")
    public CaseCorrespondenceTab clickOnRejectBtn(){
        rejectBtn.click();
        return new CaseCorrespondenceTab(getDriver());
    }

    public CaseCorrespondenceSection(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
