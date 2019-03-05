package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.gl.E;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import javax.annotation.WillNotClose;
import java.awt.event.KeyEvent;
import java.lang.annotation.Repeatable;
import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class Steps {

    private WebDriver driver;

    private HashMap<String, WebElement> namedElements;
    private List<WebElement> selectedElements;
    private WebElement selectedElement;

    public Steps() {
        this.namedElements = new HashMap<>();
        this.selectedElements = new ArrayList<>();
    }

    /**Opening Browser*/

    @Given("^I? ?open (?:the)? ?browser$")
    public void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Given("^I? ?launch (?:the )? ?browser$")
    public void openBrowserAlias() {
        openBrowser();
    }

    @Given("^(?:the)? ?browser (?:is|has been) (?:launched|opened|open)$")
    public void openBrowserAlias2() {
        openBrowser();
    }

    @Given("(?:the)? ?\"([^\"]*)\" browser is open")
    public void openGivenWebBrowser(String browserName) throws Throwable {
        launchBrowser(browserName);
    }

    @Given("^I (?:choose|select|am using) ?(?:the)? driver \"([^\"]*)\"$")
    public void selectDriver(String driver) throws Throwable {
        openGivenWebBrowser(driver);
    }

    private void launchBrowser(String browserName) throws SeleniumStepException {
        if (browserName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver(options);
        } else {
            throw new SeleniumStepException("Browser " + browserName + " is not supported");
        }
    }

    /** Actions on WebBrowser*/

    @Given("^I? ?(?:(?:go to)|(?:visit)) (?:the)? ?(?:website|url) \"([^\"]*)\"$")
    public void goToUrl(String url) {
        driver.get(url);
    }

    @Given("^(?:the)? ?window is maximized$")
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    @Given("^I? ?maximize (?:the)? ?window$")
    public void maximizeWindowAlias() {
        maximizeWindow();
    }

    /**Implicit waits*/

    @Given("^implicit wait is set to (\\d+) nanoseconds$")
    public void setImplicitWaitNano(long nanos) {
        driver.manage().timeouts().implicitlyWait(nanos, TimeUnit.NANOSECONDS);
    }

    @Given("^I? ?set implicit wait to (\\d+) nanoseconds$")
    public void setImplicitWaitNanoAlias(long nanos) {
        setImplicitWaitNano(nanos);
    }

    @Given("^implicit wait is set to (\\d+) milliseconds$")
    public void setImplicitWaitMs(long ms) {
        driver.manage().timeouts().implicitlyWait(ms, TimeUnit.MILLISECONDS);
    }

    @Given("^I? ?set implicit wait to (\\d+) milliseconds$")
    public void setImplicitWaitMsAlias(long ms) {
        setImplicitWaitMs(ms);
    }

    @Given("^implicit wait is set to (\\d+) seconds$")
    public void setImplicitWaitSeconds(long seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    @Given("^I? ?set implicit wait to (\\d+) seconds$")
    public void setImplicitWaitSecondsAlias(long seconds) {
        setImplicitWaitSeconds(seconds);
    }

    /**Select Element/s by Css*/

    @When("^I? ?select element by css selector using value \"([^\"]*)\"$")
    public void selectElementByCss(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.cssSelector(selector)) :
                selectedElement.findElement(By.cssSelector(selector));
    }

    @When("^I? ?select elements by css selector using value \"([^\"]*)\"$")
    public void selectElementsByCss(String selector) throws NoSuchElementException {
        selectedElements = selectedElement == null ? driver.findElements(By.cssSelector(selector)) :
                selectedElement.findElements(By.cssSelector(selector));
    }

    /**Select Element/s by xPath*/

    @When("^I? ?select element by xpath using value \"([^\"]*)\"$")
    public void selectElementByXpath(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.xpath(selector)) :
                selectedElement.findElement(By.xpath(selector));
    }

    @When("^I? ?select elements by xpath using value \"([^\"]*)\"$")
    public void selectElementsByXpath(String selector) throws NoSuchElementException {
        selectedElements = selectedElement == null ? driver.findElements(By.xpath(selector)) :
                selectedElement.findElements(By.xpath(selector));
    }

    /**Select Element by ID*/

    @When("^I? ?select element by id using value \"([^\"]*)\"$")
    public void selectElementById(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.id(selector)) :
                selectedElement.findElement(By.id(selector));
    }

    /**Select Element/s by tag*/

    @When("^I? ?select element by tag using value \"([^\"]*)\"$")
    public void selectElementByTag(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.tagName(selector)) :
                selectedElement.findElement(By.tagName(selector));
    }

    @When("^I? ?select elements by tag using value \"([^\"]*)\"$")
    public void selectElementsByTag(String selector) throws NoSuchElementException {
        selectedElements = selectedElement == null ? driver.findElements(By.tagName(selector)) :
                selectedElement.findElements(By.tagName(selector));
    }

    /**Select Element/s by class name*/

    @When("^I? ?select element by class name using value \"([^\"]*)\"$")
    public void selectElementByClassName(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.className(selector)) :
                selectedElement.findElement(By.className(selector));
    }

    @When("^I? ?select elements by class name using value \"([^\"]*)\"$")
    public void selectElementsByClassName(String selector) throws NoSuchElementException {
        selectedElements = selectedElement == null ? driver.findElements(By.className(selector)) :
                selectedElement.findElements(By.className(selector));
    }

    /**Select Element by text/partial link text*/

    @When("^I? ?select element by link text using value \"([^\"]*)\"$")
    public void selectElementByLinkText(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.linkText(selector)) :
                selectedElement.findElement(By.linkText(selector));
    }

    @When("^I? ?select element by partial link text using value \"([^\"]*)\"$")
    public void selectElementByPartialLinkText(String selector) throws NoSuchElementException {
        selectedElement = selectedElement == null ? driver.findElement(By.partialLinkText(selector)) :
                selectedElement.findElement(By.partialLinkText(selector));
    }

    /**Select WebElement from selectedElements and assigned to selectedElement*/

    @When("^I? ?select index (\\d+) from selected elements$")
    public void selectFromElements(Integer index) {
        selectedElement = selectedElements.get(index);
    }

    @When("^element at index (\\d+) (?:is selected|from results is selected)$")
    public void selectFromElementsAlias(Integer index) {
        selectFromElements(index);
    }

    @When("^I (?:select|choose|get|grab|fetch|focus in on) element number (\\d+) from (?:results|selected elements|list|elements|group)$")
    public void selectFromElementsAlias2(Integer index) {
        selectFromElements(index);
    }

    @When("^I? ?filter selected elements by xpath using value \"([^\"]*)\"$")
    public void filterSelectedElementsByXPath(String filter) {
        selectedElements.removeIf(element -> element.findElements(By.xpath(filter)).size() == 0);
    }

    @When("^I? ?filter selected elements based on if their descendents contain the text \"([^\"]*)\"$")
    public void filterSelectedElementsByText(String filterText) {
        selectedElements.removeIf(element -> element.findElements(By.xpath(".//*[contains(text(),'" + filterText + "')]")).size() == 0);
    }

    /**Selected Element and it's actions*/

    @When("^I? ?select (?:the)? ?named element \"([^\"]*)\"$")
    public void selectNamedElement(String elementName) {
        selectedElement = namedElements.get(elementName);
    }

    @When("^I? ?(?:left)? ?click (?:the)? ?selected element$")
    public void clickElement() {
        selectedElement.click();
    }

    @When("^I? ?double click selected element$")
    public void doubleClickElement() {
        Actions actions = new Actions(driver);
        actions.doubleClick(selectedElement).perform();
    }

    @When("^I? ?click and hold element$")
    public void clickAndHold() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(selectedElement).perform();
    }

    /**Convenience method - Submit a form if Element is contained within a form*/

    @When("^I? ?submit(?: form)?$")
    public void submit() {
        selectedElement.submit();
    }

    @When("^I? ?(?:(?:hover over)|(?:move to)) (?:the)? ?selected element$")
    public void hoverElement() {
        Actions actions = new Actions(driver);
        actions.moveToElement(selectedElement).perform();
    }

    @When("^I? ?(?:type|enter|input) (?:the text|text)? ?\"([^\"]*)\"$")
    public void enterText(String text) {
        selectedElement.sendKeys(text);
    }

    @And("^I enter user ([^\"]*)")
    public void iEnterUsername(String user) {
        driver.findElement(By.id("user")).sendKeys(user);
    }

    @And("^I enter password ([^\"]*)")
    public void iEnterPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);

    }

    @Given("^I log on as \"([^\"]*)\" using password \"([^\"]*)\"$")
    public void login(String userName, String password) {
        driver.findElement(By.id("user")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.tagName("button")).click();
    }

    @Then("^\"([^\"]*)\" user with email \"([^\"]*)\"$")
    public void deleteUserByEmail(String function, String email) {
        WebElement table = driver.findElement(By.xpath("//div[@class='ReactVirtualized__Grid ReactVirtualized__Table__Grid']/div[@class='ReactVirtualized__Grid__innerScrollContainer']"));

        List<WebElement> rows = table.findElements(By.className("ReactVirtualized__Table__row"));

        for (WebElement row : rows) {
            if (row.getText().contains(email)) {
                row.findElement(By.tagName("button")).click();
            }
        }
    }

    @When("^I? ?(?:press|select|click|click on)? ?(?:refresh|reload) ?(?:the)? ?(?:page)?$")
    public void refresh() {
        driver.navigate().refresh();
    }

    @When("^I? ?go back a page$")
    public void back() {
        driver.navigate().back();
    }

    @When("^I? ?(?:press|select|click|click on|go) back$")
    public void backAlias() {
        back();
    }

    @When("^I? ?go forward a page$")
    public void forward() {
        driver.navigate().forward();
    }

    @When("^I? ?(?:press|select|click|click on|go) forward$")
    public void forwardAlias() {
        forward();
    }

    /**SendKeys*/

    @When("^I? press enter key$")
    public void iPressEnterKey(){
        selectedElement.sendKeys(Keys.ENTER);
    }

    @When("^I? press enter key on dropdown$")
    public void iPressEnterKeyOnDropdown(){
        Actions actions = new Actions (driver);
        actions.sendKeys(Keys.ENTER);
        actions.perform();
    }

    /**ArrowKeys*/

    @When("^I? press arrow downKey$")
    public void iPressArrowDownKey(){
        selectedElement.sendKeys(Keys.ARROW_DOWN);
    }

    @When("^I? press arrow downKey (\\d+) times$")
    public void iPressArrowDownKeyTimes(int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(Keys.ARROW_DOWN);
        }
        actions.perform();
    }

    @When("^I? press arrow upKey$")
    public void iPressArrowUpKey(){
        selectedElement.sendKeys(Keys.ARROW_UP);
    }

    @When("^I? press arrow upKey (\\d+) times$")
    public void iPressArrowUpKeyTimes(int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(Keys.ARROW_UP);
        }
        actions.perform();
    }

    @When("^I? press arrow leftKey$")
    public void iPressArrowLeftKey(){
        selectedElement.sendKeys(Keys.ARROW_LEFT);
    }

    @When("^I? press arrow leftKey (\\d+) times$")
    public void iPressArrowLeftKeyTimes(int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(Keys.ARROW_LEFT);
        }
        actions.perform();
    }

    @When("^I? press arrow rightKey$")
    public void iPressArrowRightKey(){
        selectedElement.sendKeys(Keys.ARROW_RIGHT);
    }

    @When("^I? press arrow rightKey (\\d+) times$")
    public void iPressArrowRightKeyTimes(int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(Keys.ARROW_RIGHT);
        }
        actions.perform();
    }

    /**ArrowKeys and Enter*/

    @When("^I? press arrow downKey and enter$")
    public void iPressArrowDownKeyAndEnter() {
        selectedElement.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    @When("^I? press arrow upKey and enter$")
    public void iPressArrowUpKeyAndEnter() {
        selectedElement.sendKeys(Keys.ARROW_UP, Keys.ENTER);
    }

    @When("^I? press arrow leftKey and enter$")
    public void iPressArrowLeftKeyAndEnter(){
        selectedElement.sendKeys(Keys.ARROW_LEFT, Keys.ENTER);
    }

    @When("^I? press arrow rightKey and enter$")
    public void iPressArrowRightKeyAndEnter(){
        selectedElement.sendKeys(Keys.ARROW_RIGHT, Keys.ENTER);
    }

    /**OtherKeys*/

    @When("^I? press enter$")
    public void iPressEnter() {
        selectedElement.sendKeys(Keys.ENTER);
    }

    @When("^I? press backspace$")
    public void iPressBackspace(){
        selectedElement.sendKeys(Keys.BACK_SPACE);
    }

    @When("^I? press backspace (\\d+) times$")
    public void iPressBackspaceTimes(int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(Keys.BACK_SPACE);
        }
        actions.perform();
    }

    @When("^I? press delete$")
    public void iPressDelete(){
        selectedElement.sendKeys(Keys.DELETE);
    }

    @When("^I? press Ctrl Key$")
    public void iPressCtrlKey(){
        selectedElement.sendKeys(Keys.CONTROL);
    }

    @When("^I? press AltKey$")
    public void iPressAltKey(){
        selectedElement.sendKeys(Keys.ALT);
    }

    @When("^I? press Shift Key$")
    public void iPressShiftKey(){
        selectedElement.sendKeys(Keys.SHIFT);
    }

    @When("^I? press Spacebar Key$")
    public void iPressSpacebarKey(){
        selectedElement.sendKeys(Keys.SPACE);
    }

    @When("^I? press spacebar (\\d+) times$")
    public void iPressSpacebarTimes(int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(Keys.SPACE);
        }
        actions.perform();
    }

    @When("^I? press Tab Key$")
    public void iPressTabKey(){
        selectedElement.sendKeys(Keys.TAB);
    }

    @When("^I? press tab (\\d+) times$")
    public void iPressTabKeyTimes(int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(Keys.TAB);
        }
        actions.perform();
    }

    @When("^I? press Equals Key$")
    public void iPressEqualsKey(){
        selectedElement.sendKeys(Keys.EQUALS);
    }

    @When("^I? press Home Key$")
    public void iPressHomeKey(){
        selectedElement.sendKeys(Keys.HOME);
    }

    @When("^I? press Insert Key$")
    public void iPressInsertKey(){
        selectedElement.sendKeys(Keys.INSERT);
    }

    @When("^I? press PageUp Key$")
    public void iPressPageUpKey(){
        selectedElement.sendKeys(Keys.PAGE_UP);
    }

    @When("^I? press arrow PageUp (\\d+) times$")
    public void iPressPageUpTimes(int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(Keys.PAGE_UP);
        }
        actions.perform();
    }

    @When("^I? press PageDown Key$")
    public void iPressPageDownKey(){
        selectedElement.sendKeys(Keys.PAGE_DOWN);
    }

    @When("^I? press arrow PageDown (\\d+) times$")
    public void iPressPageDownTimes(int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(Keys.PAGE_DOWN);
        }
        actions.perform();
    }

    /**FunctionKeys*/

    @When("^I? press F1 Key$")
    public void iPressF1Key(){
        selectedElement.sendKeys(Keys.F1);
    }

    @When("^I? press F2 Key$")
    public void iPressF2Key(){
        selectedElement.sendKeys(Keys.F2);
    }

    @When("^I? press F3 Key$")
    public void iPressF3Key(){
        selectedElement.sendKeys(Keys.F3);
    }

    @When("^I? press F4 Key$")
    public void iPressF4Key(){
        selectedElement.sendKeys(Keys.F4);
    }

    @When("^I? press F5 Key$")
    public void iPressF5Key(){
        selectedElement.sendKeys(Keys.F5);
    }

    @When("^I? press F6 Key$")
    public void iPressF6Key(){
        selectedElement.sendKeys(Keys.F6);
    }

    @When("^I? press F7 Key$")
    public void iPressF7Key(){
        selectedElement.sendKeys(Keys.F7);
    }

    @When("^I? press F8 Key$")
    public void iPressF8Key(){
        selectedElement.sendKeys(Keys.F8);
    }

    @When("^I? press F9 Key$")
    public void iPressF9Key(){
        selectedElement.sendKeys(Keys.F9);
    }

    @When("^I? press F10 Key$")
    public void iPressF10Key(){
        selectedElement.sendKeys(Keys.F10);
    }

    @When("^I? press F11 Key$")
    public void iPressF11Key(){
        selectedElement.sendKeys(Keys.F11);
    }

    @When("^I? press F12 Key$")
    public void iPressF12Key(){
        selectedElement.sendKeys(Keys.F12);
    }

    /**End of sendKeys*/

    @When("^I? ?pause for (\\d+) milliseconds$")
    public void pauseMs(long milliseconds) {
        Actions actions = new Actions(driver);
        actions.pause(Duration.ofMillis(milliseconds));
    }

    @When("^I? ?pause for (\\d+) seconds$")
    public void pauseSecs(long seconds) {
        Actions actions = new Actions(driver);
        actions.pause(Duration.ofSeconds(seconds));
    }

    @When("^I? ?name (?:the)? ?selected element as \"([^\"]*)\"$")
    public void nameSelectedElement(String elementName) {
        namedElements.put(elementName, selectedElement);
    }

    // Temporary method until explicit waits are implemented
    @When("^a (\\d+) second pause$")
    public void pause(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    @Then("^I? ?check (?:the)? ?current url is \"([^\"]*)\"$")
    public void checkCurrentUrl(String expectedUrl) {
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Then("^I? ?check (?:the)? ?current url contains \"([^\"]*)\"$")
    public void checkCurrentUrlContains(String urlPart) {
        assertThat(driver.getCurrentUrl(), CoreMatchers.containsString(urlPart));
    }

    @Then("^I? ?check (?:the)? ?page title is \"([^\"]*)\"$")
    public void checkPageTitle(String pageTitle) {
        assertEquals(pageTitle, driver.getTitle());
    }

    @Then("^I? ?check (?:the)? ?page title contains \"([^\"]*)\"$")
    public void checkPageTitleContains(String expectedTitlePart) {
        assertThat(driver.getTitle(), CoreMatchers.containsString(expectedTitlePart));
    }

    @Then("^I? ?check (?:the)? ?page contains the text \"([^\"]*)\"$")
    public void checkPageContainsText(String expectedText) {
        List<WebElement> elementsFound = driver.findElements(By.xpath("//*[contains(text(),'" + expectedText + "')]"));
        assertTrue("Text " + expectedText + " not found", elementsFound.size() > 0);
    }

    @Then("^I? ?check (?:the)? ?page does not contain the text \"([^\"]*)\"$")
    public void checkPageDoesNotContainsText(String unExpectedText) throws Exception {
        List<WebElement> elementsFound = driver.findElements(By.xpath("//*[contains(text(),'" + unExpectedText + "')]"));
        assertTrue("Text " + unExpectedText + " not found", elementsFound.size() == 0);
    }

    @Then("^I? ?check (?:the)? ?element's inner text is equal to \"([^\"]*)\"$")
    public void checkInnerText(String expectedText) {
        assertEquals(expectedText, selectedElement.getText());
    }

    @Then("^I? ?check (?:the)? ?element's inner text contains \"([^\"]*)\"$")
    public void checkInnerTextContains(String expectedSubText) {
        assertThat(selectedElement.getText(), CoreMatchers.containsString(expectedSubText));
    }

    @Then("^I? ?check (?:the)? ?element's inner text does not contain \"([^\"]*)\"$")
    public void checkInnerTextDoesNotContain(String unwantedSubText) {
        List<WebElement> notFound = driver.findElements(By.xpath("//*[contains(text(),'" + unwantedSubText + "')]"));
        assertEquals(0, notFound.size());
        notFound.clear();
    }

    @Then("^I? ?check if the element's descendents contain the text \"([^\"]*)\"$")
    public void checkDescendents(String expectedText) {
        assertTrue(selectedElement == null ?
                driver.findElements(By.xpath("//*[contains(text(),'" + expectedText + "')]")).size() > 0 :
                selectedElement.findElements(By.xpath(".//*[contains(text(),'" + expectedText + "')]")).size() > 0);
    }

    @Then("^I? ?check (?:the)? ?attribute \"([^\"]*)\" exists$")
    public void iCheckAttributeExists(String attribute) {
        String expectedAttribute = selectedElement.getAttribute(attribute);
        assertNotNull(expectedAttribute);
    }

    @Then("^I? ?check (?:the)? ?element's attribute \"([^\"]*)\" is equal to \"([^\"]*)\"$")
    public void checkAttributeValue(String attribute, String value) {
        assertEquals(value, selectedElement.getAttribute(attribute));
    }

    @Then("^I? ?check (?:the)? ?element's attribute \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void checkAttributeValueContains(String attribute, String value) {
        assertThat(selectedElement.getAttribute(attribute), CoreMatchers.containsString(value));
    }

    /**Count selected number of elements in selectedElements*/

    @Then("^I? ?check (?:the)? ?number of elements found is (\\d+)$")
    public void checkNumberOfElementsFound(int expectedCount) {
        assertEquals(expectedCount, selectedElements.size());
    }

    @Then("^I? ?check (?:the)? ?number of elements found is at least (\\d+)$")
    public void checkNumberOfElementsFoundAtLeast(int expectedCount) {
        assertTrue(selectedElements.size() >= expectedCount);
    }

    @Then("^I? ?check (?:the)? ?element is displayed$")
    public void checkElementIsDisplayed() {
        assertTrue(selectedElement.isDisplayed());
    }

    @Then("^I? ?check (?:the)? ?element is selected$")
    public void checkElementIsSelected() {
        assertTrue(selectedElement.isSelected());
    }

    @Then("^I? ?check (?:the)? ?element is enabled$")
    public void checkElementIsEnabled() {
        assertTrue(selectedElement.isEnabled());
    }

    /**Clear selectedElement/s single and list*/

    @Then("^I? ?clear (?:the)? ?selected elements$")
    public void clearSelectedElements() {
        selectedElement = null;
        selectedElements = new ArrayList<>();
    }

    /**Clear named Element/s*/

    @Then("^I? ?clear (?:the)? ?named elements$")
    public void clearNamedElements() {
        namedElements = new HashMap<>();
    }

    @Then("^I? ?(?:close|quit)(?:(?: the)? browser)?$")
    public void closeBrowser() {
        driver.close();
    }


}
