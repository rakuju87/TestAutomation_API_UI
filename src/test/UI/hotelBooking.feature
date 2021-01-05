@UITests
Feature: Hotel booking test
  Scenario Outline: Scenario Outline name: User want to successfully book a hotel booking
    Given user navigate to phpTravel
    Then user choose destination as <destination>
    And search for <checkin> date and <checkout> date
    Examples:
      |destination         |checkin    |checkout   |
      |Alzer Hotel Istanbul|07/02/2021 |07/03/2021 |