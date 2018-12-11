Feature: As a user, I can view detailed information of an item

Scenario: user can see the detailed information of a book
    Given book "title" "author" "url" "isbn" "description" exists in the application
    And command "details" with id 1 is entered
    When user does nothing
    Then the detailed information view of the book is shown with title "title", author "author", url "url", isbn "isbn" and description "description"

Scenario: user can see the detailed information of a video
    Given video "title" "author" "url" "description" exists in the application
    And command "details" with id 1 is entered
    When user does nothing
    Then the detailed information view of the video is shown with title "title", author "author", url "url" and description "description"
