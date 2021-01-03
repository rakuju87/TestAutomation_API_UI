@UITests
Feature: Hotel booking test
  Scenario Outline: Scenario Outline name: User want to successfully book a hotel booking
    Given user navigate to phpTravel
    Then user choose destination as "<destination>"
    Examples:
      |destination     |
      |Istanbul, Turkey|