Feature: As a user I want to use the application through command line interface

    Scenario: user can use invalid input
        Given command "abc" is entered
        When user does nothing
        Then system will respond with "unknown option"
        And system will respond with "Welcome to the CS literature recommendation system!"

    Scenario: user can add a new item
        Given command "new" is entered
        When user does nothing
        Then system will respond with "Type: (book, video, blog post)"