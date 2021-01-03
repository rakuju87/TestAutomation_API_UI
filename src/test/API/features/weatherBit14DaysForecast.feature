#@APItesting
Feature: Weather forecast

  Scenario Outline: A happy holidaymaker want to check for weather forecast for 14 days
    Given I want to go to <cityName> city
    When I check weather response contains <cityName>
    Then I check temp on <day> is between <minTemp> to <maxTemp>
    And I check it didn't rained before <day> more than <precip>
    Then I check that it didn't snowed before <day> more than <snow>

    Examples:
      | cityName    |day      |minTemp|maxTemp|precip|snow|
      | Delhi       |Thursday |10     |30     | 20   | 0  |
      | Dubai       |Thursday |10     |30     | 50   | 0  |
