Feature: Google

  Scenario: Search something on google
    Given I open the browser
    And I go to the url "https://www.google.com"
    And I select element by xpath using value "/html/body/div/div[3]/form/div[2]/div/div[1]/div/div[1]/input"
    And I enter the text "In an orange"
    And I submit form
    