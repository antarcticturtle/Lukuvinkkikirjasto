Feature: As a user I want to add, edit, delete, modify and list items

    Scenario: user can list when there are no items added yet
        Given command "list" is entered
        When user does nothing
        Then system will respond with "No items added yet"

    Scenario: user can list one item
        Given command "list" is entered
        And item "Hello World!" exists in the application
        When user does nothing
        Then system will respond with "Hello World!"