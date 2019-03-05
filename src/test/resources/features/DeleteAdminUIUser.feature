Feature: Delete Admin UI User


  Scenario: Delete Admin UI User
    Given I open the browser
    And visit the url "http://adminuihost:3000"
    And I log on as "admin@neueda.com" using password "admin"

    When I select element by xpath using value "//button[@title='Users']"
    And I left click the selected element
    Then I clear the selected elements
    Then "Delete" user with email "sasha.simms-rowan@neueda.com"
    Then I set implicit wait to 5 seconds
    Then I select element by xpath using value "/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/button[1]"
    Then I left click the selected element
    Then I clear the selected elements
    Then I click refresh the page
    Then I check the element's inner text does not contain "sasha.simms-rowan@neueda.com"
    And close the browser