Feature: As a user I want to see the available commands

    Scenario: user can see the available commands
        Given command "help" is entered
        When user does nothing
        Then system will respond with 
            """
            quit      = quit the application
            help      = see these commands
            list      = list items
            list by   = sort and list items
            details   = see detailed information of an item
            edit      = edit item
            search    = search items
            delete    = delete item
            read      = mark items as read
            """