Feature: As a user I want to mark items as read

    Scenario: User can't choose the id of the items that doesn't exist
        Given command "read" is entered
        And book "Title" "Author" "url" "isbn" "description" exists in the application
        And command "2" is entered
        And command "1" is entered
        And user does nothing
        Then system will respond with "Please enter a valid id"

    Scenario: User can mark item as read
        Given command "read" is entered
        And book "title" "author" "url" "isbn" "description" exists in the application
        And command "1" is entered
        And command "details" is entered
        And command "1" is entered
        And user does nothing
        Then the detailed information view of the read book is shown with title "title", author "author", url "url", isbn "isbn" and description "description"
