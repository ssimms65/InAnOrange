Feature: Admin UI Logout

  Scenario: Admin UI Logout
    Given I open the browser
    And visit the url "http://adminuihost:3000"
    And I log on as "admin@neueda.com" using password "admin"
    #Log out
    When I select element by xpath using value "/html/body/div/div/header/div/div/div[3]/div[1]/div[2]/div/a/span"
    And I double click selected element

    Then I close the browser