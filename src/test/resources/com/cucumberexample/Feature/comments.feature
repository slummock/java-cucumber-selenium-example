Feature: Comments

#   Scenario: User makes a comment while logged in
#     Given User has logged in
#     And Posted a comment
#     Then Comment appears on the page

  Scenario: User makes a comment on BBC blog post
    Given User is signed out
    And Sign in as "bbc-email" with password "bbc-password"
    And User navigates to "http://www.bbc.co.uk/blogs/test/entries/f5f3031a-1a29-44ee-b2f8-86e78bfd57b0"
    And User clicks the comments link
    And Submits a comment
    Then Comment appears on the page