Feature: As a user I want to search items

    Scenario: if the search query doesn't match any items, a message is presented
        Given command "search" is entered
        And book "Think Python" "O'Reilly" "www.amazon.com" "0123456789" "Great book to learn Python" exists in the application
        And command "java" is entered
        When user does nothing
        Then system will respond with "Search the library"
        Then system will respond with "No items found"

    Scenario: if a user enters an invalid search query a warning message is presented
        Given command "search" is entered
        And book "Think Python" "O'Reilly" "www.amazon.com" "0123456789" "Great book to learn Python" exists in the application
        And command "" is entered
        And command "java" is entered
        When user does nothing
        Then system will respond with "Please enter a keyword"
        Then system will respond with "Search the library"
        Then system will respond with "No items found"

    Scenario: only items matching the search query are listed
        Given command "search" is entered
        And book "Think Python" "O'Reilly" "www.amazon.com" "0123456789" "Great book to learn Python" exists in the application
        And book "Eloquent Javascript" "Marijn Haverbeke" "www.amazon.com" "555" "Great book to learn Javascript" exists in the application
        And command "Python" is entered
        When user does nothing
        Then system will respond with "(id: 1) Book: Think Python by O'Reilly"

    Scenario: search matches any columns
        Given command "search" is entered
        And book "Python" "a" "url" "88" "book" exists in the application
        And book "title" "Python" "url" "88" "book" exists in the application
        And book "title" "a" "Python" "88" "book" exists in the application
        And book "title" "a" "url" "Python" "book" exists in the application
        And book "title" "a" "url" "88" "Python" exists in the application
        And command "Python" is entered
        When user does nothing
        Then system will respond with "(id: 1) Book: Python by a"
        Then system will respond with "(id: 2) Book: title by Python"
        Then system will respond with "(id: 3) Book: title by a"
        Then system will respond with "(id: 4) Book: title by a"
        Then system will respond with "(id: 5) Book: title by a"
