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
        Then the item is listed with correct id "1", type "Book", title "Think Python" and author "O'Reilly"

    Scenario: search matches any columns
        Given command "search" is entered
        And book "Python" "a" "url" "88" "book" exists in the application
        And book "title" "Python" "url" "88" "book" exists in the application
        And book "title" "a" "Python" "88" "book" exists in the application
        And book "title" "a" "url" "Python" "book" exists in the application
        And book "title" "a" "url" "88" "Python" exists in the application
        And command "Python" is entered
        When user does nothing
        Then the item is listed with correct id "1", type "Book", title "Python" and author "a"
        Then the item is listed with correct id "2", type "Book", title "title" and author "Python"
        Then the item is listed with correct id "3", type "Book", title "title" and author "a"
        Then the item is listed with correct id "4", type "Book", title "title" and author "a"
        Then the item is listed with correct id "5", type "Book", title "title" and author "a"
