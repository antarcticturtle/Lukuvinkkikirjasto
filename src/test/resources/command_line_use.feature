Feature: As a user I want to use the application through command line interface

    Scenario: user can use invalid input
        Given command "abc" is entered
        When user does nothing
        Then system will respond with "unknown option"
        And system will respond with "Welcome to the CS literature recommendation system!"

    Scenario: user can add a new book (not yet implemented)
        Given command "new" is entered
        When user does nothing
        Then system will respond with "Here you can add a book (not implemented yet)"