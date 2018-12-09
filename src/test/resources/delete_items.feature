Feature: As a user I want to delete items

    Scenario: user can delete an item
        Given command "delete" is entered
        And command "0" is entered
        And command "" is entered
        When items are listed
        And user does nothing
        And system will respond with "Enter the id of the item (leave empty to cancel)"
        And system will respond with "No items added yet"

    Scenario: user can't delete an item with wrong id
        Given command "delete" is entered
        And item "Title" "Author" "url" exists in the application
        And command "7" is entered
        And command "" is entered
        When items are listed
        And user does nothing
        And system will respond with "Enter the id of the item (leave empty to cancel)"
        And system will respond with "Please enter a valid id"