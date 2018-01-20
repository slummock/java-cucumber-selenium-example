Feature: Comments

  Scenario: User makes a comment while logged in
    Given User has logged in
    And Posted a comment
    Then Comment appears on the page