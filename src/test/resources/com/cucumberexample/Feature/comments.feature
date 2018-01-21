Feature: BBC Blog Comments

  Scenario: User can post a valid comment
    Given User is signed out
    And Sign in as "bbc-email" with password "bbc-password"
    And User navigates to "http://www.bbc.co.uk/blogs/test/entries/f5f3031a-1a29-44ee-b2f8-86e78bfd57b0"
    And User clicks the comments link
    And Submits a comment
    Then Comment appears on the page

  Scenario: User can't post an empty comment
    Given User is signed out
    And Sign in as "bbc-email" with password "bbc-password"
    And User navigates to "http://www.bbc.co.uk/blogs/test/entries/f5f3031a-1a29-44ee-b2f8-86e78bfd57b0"
    And User clicks the comments link
    And Submits an empty comment
    Then Comment error message appears