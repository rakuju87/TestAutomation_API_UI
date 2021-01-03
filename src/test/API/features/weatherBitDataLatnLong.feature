@APItesting
Feature: Weather Data for multiple places in the world

  Scenario Outline: I want to get Current Weather Data for multiple places in the world based on Lat and Lon
    Given I check current weather for <latitude> and <longitude>
    When I check weather city list response contains <cityName>
    Then I check temperature is between <minTemp> to <maxTemp>
    And I check it didn't rained more than <precip>
    Then I check that it didn't snowed more than <snow>

    Examples:
      | latitude    |longitude  | cityName  |minTemp|maxTemp|precip|snow |
      | 28.644800   |77.216721  | New Delhi | 0     |20     |2     |10   |

  Scenario Outline: I want to get Current Weather Data for multiple places in the world based on postal code
    Given I check current weather using <postal_code> and <country>
    When I check weather city list response contains <cityName>
    Then I check temperature is between <minTemp> to <maxTemp>
    And I check it didn't rained more than <precip>
    Then I check that it didn't snowed more than <snow>

    Examples:
      | postal_code |country    | cityName  |minTemp|maxTemp|precip|snow |
      | 3400        |TR         | Tarsus    |0     |20     |2     |10   |
