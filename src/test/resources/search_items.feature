Feature: As a user I want to search items

    Scenario: if the search query doesn't match any items, a message is presented
        Given command "search" is entered
        And item "Think Python" "O'Reilly" "www.amazon.com" exists in the application
        And command "java" is entered
        When user does nothing
        Then system will respond with "Search the library"
        Then system will respond with "No items found"

    Scenario: if a user enters an invalid search query a warning message is presented
        Given command "search" is entered
        And item "Think Python" "O'Reilly" "www.amazon.com" exists in the application
        And command "" is entered
        And command "java" is entered
        When user does nothing
        Then system will respond with "Please enter a keyword"
        Then system will respond with "Search the library"
        Then system will respond with "No items found"