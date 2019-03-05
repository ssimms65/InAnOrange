Feature: Logon to Admin UI

  Scenario Outline: Admin log on
    Given I open the browser
    And visit the url "http://adminuihost:3000"
    When I select element by xpath using value "//*[@id='user']"
    And I enter user <user>
    And I clear the selected elements
    When I select element by xpath using value "//*[@id='password']"
    And I enter password <password>
    And I clear the selected elements
    When I select element by xpath using value "/html/body/div/div/div/div/form/div/div[2]/button"
    And I left click the selected element

    #Log off
    #And I clear the selected elements
    #When I select element by xpath using value "/html/body/div/div/header/div/div/div[3]/div[1]/div[2]/div/a/span"
    #And I double click selected element

   # And I close the browser

    Examples:
      | user                           | password              |
   #   | admin@neueda.com               | admin                 |
      | sasha.simms-rowan@neueda.com   | #swims65              |

