package com.advalent.automation.impl.pages.login;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.dto.User;
import com.advalent.automation.api.pages.dashboard.IDashboardPage;
import com.advalent.automation.api.pages.login.ILoginPage;
import com.advalent.automation.components.inputelements.TextBox;
import com.advalent.automation.components.webelement.WebElements;
import com.advalent.automation.impl.pages.dashboard.SubroPointDashboard;
import com.advalent.automation.impl.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Auth0LoginPage extends AbstractLoginPage implements ILoginPage {


    private LoginPageWebElements webElements;

    public Auth0LoginPage(WebDriver driver) {
        super(driver);
        this.webElements = new LoginPageWebElements(driver);
    }

    @Override
    TextBox getUserNameTextBox() {
        return webElements.username;
    }

    @Override
    TextBox getPasswordTextBox() {
        return webElements.password;
    }

    @Override
    WebElement getLoginButton() {
        return webElements.submitBtn;
    }

    @Override
    public IDashboardPage loginWith(User user) {
        enterUserName(user.getUserId()).enterPassword(user.getPassword()).submit();
        SubroPointDashboard subroPointDashboard = PageFactory.initElements(getDriver(), SubroPointDashboard.class);
        return subroPointDashboard;
    }


    @Override
    public IDashboardPage loginWith(String automationId) {
        return null;
    }

    @Override
    public String getErrorMessage() {
        return webElements.errorMessage.getText();
    }

    @Override
    public User getCurrentUser() {
        return new User(webElements.username.getValue(), webElements.password.getValue());
    }

    @Override
    public boolean isFullyLoaded() {
        return isLoginPanelDisplayed();
    }

    @Override
    public boolean isLoginPanelDisplayed() {
        return webElements.loginPanel.isDisplayed();
    }

    @Override
    public void waitTillLoginPanelIsLoaded(int waitTime) {
        WaitUtils.waitUntil(getDriver(), waitTime, input -> isLoginPanelDisplayed());
    }

    private static class LoginPageWebElements {


        private LoginPageWebElements(WebDriver driver) {
            PageFactory.initElements(driver, this);
        }

        @CustomElement(xpath = "//*[@id='content']/div[2]/form/div/div[2]/div/div/input[1]")
        public TextBox username;
        @CustomElement(xpath = "//*[@id='content']/div[2]/form/div/div[2]/div/div/input[2]"
        )
        public TextBox password;
        @FindBy(xpath = "//*[@id='content']/div[2]/form/div/div[2]/div")
        public WebElement loginPanel;
        @FindBy(xpath = "//*[@id=\"content\"]/div[2]/form/div/div[2]/div/div/p[1]")
        private WebElement errorMessage;
        @FindBy(xpath = "//*[@id='content']/div[2]/form/div/div[2]/div/div/button")
        private WebElement submitBtn;
    }
}
