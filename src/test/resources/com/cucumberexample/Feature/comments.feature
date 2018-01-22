Feature: BBC Blog Comments

  Scenario: User can post a valid comment
    Given User is signed out
    And User signs in
    And User navigates to "http://www.bbc.co.uk/blogs/test/entries/f5f3031a-1a29-44ee-b2f8-86e78bfd57b0"
    And User clicks the comments link
    And Submits a comment
    Then Comment appears on the page

  Scenario: User can't post an empty comment
    Given User is signed out
    And User signs in
    And User navigates to "http://www.bbc.co.uk/blogs/test/entries/f5f3031a-1a29-44ee-b2f8-86e78bfd57b0"
    And User clicks the comments link
    And Submits an empty comment
    Then Comment error message appears

  @manual
  Scenario: Unregistered user can sign up and make a comment
    Given User is signed out
    And User registers a new account
    And Confirms registration in email
    And User navigates to "http://www.bbc.co.uk/blogs/test/entries/f5f3031a-1a29-44ee-b2f8-86e78bfd57b0"
    And User clicks the comments link
    And Submits a valid comment
    Then Comment appears on the page
