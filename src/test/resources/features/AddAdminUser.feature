Feature: Add Admin User

  Scenario: Add admin user
    Given I open the browser
    And go to the url "http://localhost:3000/"

    Given I select element by id using value "user"
    And I enter the text "admin@neueda.com"
    And I clear the selected elements
    And I select element by id using value "password"
    And I enter the text "admin"
    And I clear the selected elements

    Given I select element by class name using value "btn-primary"
    And click the selected element
    And clear the selected elements

    Given I select element by css selector using value "button.btn:nth-child(2)"
    And click the selected element
    And clear the selected elements

    Given I select element by css selector using value ".btn-group > button:nth-child(1)"
    And click the selected element
    And clear selected elements

    When I select element by css selector using value "#username"
    And I enter the text "sasha.simms-rowan@neueda.com"
    And clear the selected elements

  # Select the admin radio
    Then I select element by css selector using value "div.ReactVirtualized__Table__row:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"
    And I click the selected element
    And clear the selected elements

  # Check the active box
    Then I select element by css selector using value "#active"
    And click the selected element
    And clear the selected elements

  # Click on accept
    Then I select element by css selector using value "div.col-sm-1:nth-child(2) > button:nth-child(1)"
    And click the selected element
    And clear the selected elements
    And I set implicit wait to 5 seconds

  # Press ok to confirm
    Then I select element by css selector using value ".btn-sm"
    And click the selected element
    And clear the selected elements

  # Check the list of users contains the newly added admin
    Then I check the page contains the text "sasha.simms-rowan@neueda.com"
  #And close the browser