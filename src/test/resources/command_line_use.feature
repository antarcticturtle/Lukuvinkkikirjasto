Feature: As a user I want to use the application through command line interface

    Scenario: user can use invalid input
        Given command "abc" is entered
        When user does nothing
        Then system will respond with "unknown option"

    Scenario: user can add a new item
        Given command "new" is entered
        When user does nothing
        Then system will respond with "Type: (book, video, blog post, podcast)"

    Scenario: user can exit the program
        Given command "quit" is entered
        When user does nothing
        Then system will respond with "Thank you for using Lukuvinkkikirjasto, hope to see you soon!"