package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import org.apache.xalan.templates.ElemApplyImport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    // WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUserLogsIn(String username, String password) {
        logInWith(username, password);
    }

    @Given("command new user is selected")
    public void commandNewUserSelected() {
        openRegisterPage();
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordAndConfirmationAreEntered(String username, String password) {
        registerWith(username, password, password);
    }

    @Then("a new user is created")
    public void newUserCreated() {
        pageHasContent("Welcome to Ohtu Application");
    }

    @When("a too short username {string} and valid password {string} and matching password confirmation are entered")
    public void tooShortUsernameAndValidPasswordAndConfirmation(String username, String password) {
        registerWith(username, password, password);
    }

    @Then("user is not created and error {string} is reported")
    public void userNotCreatedAndErrorReported(String error) {
        pageHasContent(error);
    }

    @When("a valid username {string} and a too short password {string} and matching password confirmation are entered")
    public void validUsernameAndTooShortPasswordAndMatchingConfirmation(String username, String password) {
        registerWith(username, password, password);
    }

    @When("a valid username {string}, a valid password {string} and a non-matching password confirmation {string} are entered")
    public void validUsernameValidPasswordAndNonMatchingConfirmation(String username, String password,
            String confirmation) {
        registerWith(username, password, confirmation);
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void userSuccesfullyCreated(String username, String password) {
        openRegisterPage();
        registerWith(username, password, password);
        var element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }

    @When("the existing username {string} with correct password {string}")
    public void existingUsernameWithCorrectPassword(String username, String password) {
        logInWith(username, password);
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userWithUsernameAndPasswordTriedButFailedToCreate(String username, String password) {
        openRegisterPage();
        registerWith(username, password, password);
        assertTrue(driver.getPageSource().contains("Create username and give password"));
    }

    @When("the tried username {string} and password {string} are entered")
    public void triedUsernameAndPasswordEntered(String username, String password) {
        logInWith(username, password);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void registerWith(String username, String password, String confirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        var element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private void openRegisterPage() {
        driver.get(baseUrl);
        var element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
}
