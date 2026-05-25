package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;


    public void enterUsername(String user) {
        username.clear();
        username.sendKeys(user);
    }

    public void enterPassword(String pwd){
        password.clear();
        password.sendKeys(pwd);
    }

    public void clickLogin(){
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}

