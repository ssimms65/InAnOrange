Feature: Instrument View Admin Ui

  Scenario: Instrument tab view
    Given I open the browser
    And visit the url "http://adminuihost:3000"
    And I log on as "admin@neueda.com" using password "admin"
    When I select element by xpath using value "//button[@title='Instruments']"
    And I left click the selected element

  Scenario: View Markets dropdown
    Given I open the browser
    And visit the url "http://adminuihost:3000"
    And I log on as "admin@neueda.com" using password "admin"
    When I select element by xpath using value "//button[@title='Instruments']"
    And I left click the selected element
    #Onto Markets Dropdown
    Then I clear the selected elements
    When I select element by xpath using value "//button[@label='Markets']"
    And I left click the selected element
    #Selecting From Dropdown
    When I press arrow downKey 5 times
    When I press enter key on dropdown



  Scenario: View Products dropdown
    Given I open the browser
    And visit the url "http://adminuihost:3000"
    And I log on as "admin@neueda.com" using password "admin"
    When I select element by xpath using value "//button[@title='Instruments']"
    And I left click the selected element

    Then I clear the selected elements
    When I select element by xpath using value "//button[@label='Products']"
    And I left click the selected element