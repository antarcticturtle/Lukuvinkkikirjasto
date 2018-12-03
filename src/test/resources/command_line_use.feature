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

    Scenario: user can see tha available commands
        Given command "help" is entered
        When user does nothing
        Then system will respond with 
            """
            quit = quit the application
            help = see these commands
            new = add a new item
            list = list items
            list by = sort and list items
            details = see more detailed information of an item
            edit = edit item
            search = search items
            delete = delete item
            """